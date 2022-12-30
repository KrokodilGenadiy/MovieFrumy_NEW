package com.zaus_app.moviefrumy_20.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.zaus_app.moviefrumy_new.data.Film
import com.zaus_app.moviefrumy_new.databinding.FilmItemBinding


class FilmViewHolder(binding: FilmItemBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description

    init {
        binding.root.setOnClickListener {
            clickAtPosition(adapterPosition)
        }
    }

    fun bind(film: Film) {
        title.text = film.title
        poster.setImageResource(film.poster)
        description.text = film.description
    }

}