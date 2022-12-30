package com.zaus_app.moviefrumy_2.view.rv_adaptes

import androidx.recyclerview.widget.DiffUtil
import com.zaus_app.moviefrumy_new.data.Film

class FilmDiffCallBack : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
