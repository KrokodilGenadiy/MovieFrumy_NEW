package com.zaus_app.moviefrumy_new.view.fragments.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.moviefrumy_2.view.rv_adaptes.FilmDiff
import com.zaus_app.moviefrumy_20.view.rv_adaptes.FilmAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration
import com.zaus_app.moviefrumy_new.R
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.databinding.FragmentHomeBinding
import com.zaus_app.moviefrumy_new.utils.AnimationHelper


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val filmsAdapter by lazy {
        FilmAdapter(object : FilmAdapter.OnItemClickListener {
            override fun click(position: Int) {
                Toast.makeText(requireContext(),position.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)
        binding.mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}