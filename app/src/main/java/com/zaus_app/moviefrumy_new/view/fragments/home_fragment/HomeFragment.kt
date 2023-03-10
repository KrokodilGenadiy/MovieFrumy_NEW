package com.zaus_app.moviefrumy_new.view.fragments.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.moviefrumy_20.view.rv_adaptes.FilmsAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.databinding.FragmentHomeBinding
import com.zaus_app.moviefrumy_new.utils.AnimationHelper
import com.zaus_app.moviefrumy_new.view.MainActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val filmsAdapter by lazy {
        FilmsAdapter(object : FilmsAdapter.OnItemClickListener {
            override fun click(film: Film) {
                (requireActivity() as MainActivity).launchDetailsFragment(film)
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
        initSearch()
        getFilms()
    }

    private fun getFilms() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies().collectLatest { movies ->
                filmsAdapter.submitData(viewLifecycleOwner.lifecycle, movies)
            }
        }
    }

    fun initSearch() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }
        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    viewModel.setQuery(newText)
                    getFilms()
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal class DebouncingQueryTextListener(
        lifecycle: Lifecycle,
        private val onDebouncingQueryTextChange: (String?) -> Unit
    ) : SearchView.OnQueryTextListener {
        var debouncePeriod: Long = 500

        private val coroutineScope = lifecycle.coroutineScope

        private var searchJob: Job? = null

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                newText?.let {
                    delay(debouncePeriod)
                    onDebouncingQueryTextChange(newText)
                }
            }
            return false
        }
    }
}