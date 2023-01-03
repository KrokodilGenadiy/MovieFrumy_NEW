package com.zaus_app.moviefrumy_new.view.fragments.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.moviefrumy_20.view.rv_adaptes.FilmsAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.databinding.FragmentHomeBinding
import com.zaus_app.moviefrumy_new.utils.AnimationHelper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val filmsAdapter by lazy {
        FilmsAdapter(object : FilmsAdapter.OnItemClickListener {
            override fun click(film: Film) {
               // (requireActivity() as MainActivity).launchDetailsFragment(film)
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
        initGetFilms()
    }

    private fun initGetFilms() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies().collectLatest { movies ->
                filmsAdapter.submitData(viewLifecycleOwner.lifecycle, movies)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}