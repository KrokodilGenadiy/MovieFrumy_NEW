package com.zaus_app.moviefrumy_20.view.rv_adaptes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.zaus_app.moviefrumy_20.view.rv_viewholders.FilmViewHolder
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.databinding.FilmItemBinding
import com.zaus_app.moviefrumy_new.view.rv_adapters.FilmDiffCallBack

class FilmsAdapter(private val clickListener: OnItemClickListener) :
    PagingDataAdapter<Film, FilmViewHolder>(
        FilmDiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding) {
            clickListener.click(getItem(it)!!)
        }
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    interface OnItemClickListener {
        fun click(film: Film)
    }
}