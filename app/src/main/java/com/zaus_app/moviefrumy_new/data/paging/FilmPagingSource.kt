package com.zaus_app.moviefrumy_new.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.data.entity.TmdbResultsDto
import com.zaus_app.moviefrumy_new.domain.BaseInteractor
import com.zaus_app.moviefrumy_new.utils.Converter
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class FilmPagingSource(
    private val query: String,
    private val interactor: BaseInteractor
): PagingSource<Int, Film>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = if (query.isEmpty())
                interactor.getFilmsFromApi(pageIndex)
            else
                interactor.getFilmsByQuery(query,pageIndex)
            val films = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
            val nextKey =
                if (films.isEmpty()) {
                    null
                } else {
                    pageIndex +1
                }
            LoadResult.Page(
                data = films,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val TMDB_STARTING_PAGE_INDEX = 1
    }
}


