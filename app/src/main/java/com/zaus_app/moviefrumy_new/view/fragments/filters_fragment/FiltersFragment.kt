package com.zaus_app.moviefrumy_new.view.fragments.filters_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zaus_app.moviefrumy_new.R
import com.zaus_app.moviefrumy_new.databinding.FragmentFiltersBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FiltersFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFiltersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FiltersFragmentViewModel by viewModels()
    private val categoryList: HashMap<String, String> by lazy {
        hashMapOf(
            POPULAR_CATEGORY to resources.getString(R.string.popular),
            TOP_RATED_CATEGORY to resources.getString(R.string.top_rated),
            UPCOMING_CATEGORY to resources.getString(R.string.upcoming),
            NOW_PLAYING_CATEGORY to resources.getString(R.string.playing)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoryChooser()
        initConfirmButton()
    }

    fun initCategoryChooser() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentCategory.collectLatest {
                binding.autoCompleteTextView.setText(categoryList[it])
            }
        }
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.category_item, categories)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                when (parent.getItemAtPosition(position).toString()) {
                    resources.getString(R.string.popular) -> viewModel.interactor.saveDefaultCategoryToPreferences(
                        POPULAR_CATEGORY
                    )
                    resources.getString(R.string.top_rated) -> viewModel.interactor.saveDefaultCategoryToPreferences(
                        TOP_RATED_CATEGORY
                    )
                    resources.getString(R.string.upcoming) -> viewModel.interactor.saveDefaultCategoryToPreferences(
                        UPCOMING_CATEGORY
                    )
                    resources.getString(R.string.playing) -> viewModel.interactor.saveDefaultCategoryToPreferences(
                        NOW_PLAYING_CATEGORY
                    )
                }

            }
    }

    fun initConfirmButton() {
        //TODO need to add logic to refresh recycler on home page
        binding.confirmButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val POPULAR_CATEGORY = "popular"
        private const val TOP_RATED_CATEGORY = "top_rated"
        private const val UPCOMING_CATEGORY = "upcoming"
        private const val NOW_PLAYING_CATEGORY = "now_playing"
    }
}
