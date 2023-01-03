package com.zaus_app.moviefrumy_20.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zaus_app.moviefrumy_new.data.ApiConstants
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.databinding.FilmItemBinding


class FilmViewHolder(binding: FilmItemBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description
    private val ratingDonut = binding.ratingDonut

    init {
        binding.root.setOnClickListener {
            clickAtPosition(absoluteAdapterPosition)
        }
    }

    fun bind(film: Film) {
        title.text = film.title
        Glide.with(itemView)
            .load(ApiConstants.IMAGES_URL + "w780" + film.poster)
            .centerCrop()
            .into(poster)
        description.text = film.description
        ratingDonut.setProgress((film.rating * 10).toInt())
        ratingDonut.animateProgress()
    }

}