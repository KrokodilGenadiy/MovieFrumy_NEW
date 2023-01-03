package com.zaus_app.moviefrumy_new.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zaus_app.moviefrumy_new.data.ApiConstants
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val film = arguments?.get("film") as Film
        setFilmsDetails(film)
    }

    private fun setFilmsDetails(film: Film) {
        binding.title.text = film.title
        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w780" + film.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        binding.detailsDescription.text = film.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}