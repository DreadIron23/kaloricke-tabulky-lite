package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import janmokry.kaloricketabulkylite.feature.foodexplorer.R
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.FragmentFoodExplorerBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodExplorerUiState
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodExplorerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodExplorerFragment : Fragment(R.layout.fragment_food_explorer) {

    private val binding by viewBinding(FragmentFoodExplorerBinding::bind)

    private val viewModel: FoodExplorerViewModel by viewModels()

    private val foodExplorerAdapter = FoodExplorerRecyclerViewAdapter {
        binding.searchView.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewTransitions(view)

        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = foodExplorerAdapter
        }

        setupSearchView()

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    FoodExplorerUiState.Loading -> Unit
                    is FoodExplorerUiState.Success -> showSuccessState(uiState)
                    is FoodExplorerUiState.Error -> showErrorState(uiState)
                    FoodExplorerUiState.NotFound -> showNotFoundState()
                }
            }
        }

        binding.searchBar.startOnLoadAnimation(savedInstanceState)
    }

    private fun showNotFoundState() = with(binding) {
        list.isVisible = false
        notFound.isVisible = true
    }

    private fun showSuccessState(uiState: FoodExplorerUiState.Success) = with(binding) {
        list.isVisible = true
        notFound.isVisible = false

        foodExplorerAdapter.feedItems(uiState.foodList)
    }

    private fun showErrorState(error: FoodExplorerUiState.Error) = with(binding) {
        Snackbar.make(searchView, R.string.generic_error, Snackbar.LENGTH_LONG).show()
    }

    private fun setupSearchView() = with(binding.searchView) {
        editText.addTextChangedListener {
            val query = it.toString()
            viewModel.onSearchQueryChange(query)
        }

        editText.setOnEditorActionListener { _, _, _ ->
            submitSearchQuery(
                binding.searchBar,
                this,
                text.toString()
            )
            false
        }

        val onBackPressedCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(false) {
                override fun handleOnBackPressed() {
                    if (isAdded) {
                        submitSearchQuery(binding.searchBar, this@with, text.toString())
                        hide()
                    }
                }
            }
        toolbar.setNavigationOnClickListener {
            submitSearchQuery(binding.searchBar, this, text.toString())
        }
        requireActivity().apply {
            onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        }
        addTransitionListener { _, _, newState: SearchView.TransitionState ->
            onBackPressedCallback.isEnabled = newState == SearchView.TransitionState.SHOWN
        }
    }

    private fun submitSearchQuery(searchBar: SearchBar, searchView: SearchView, query: String) {
        searchBar.text = query
        searchView.hide()
    }

    private fun setupViewTransitions(view: View) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        exitTransition = MaterialElevationScale(false).apply {
            duration = TRANSITION_DURATION
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = TRANSITION_DURATION
        }
    }

    private fun SearchBar.startOnLoadAnimation(bundle: Bundle?) {
        // Don't start animation on rotation.
        if (bundle == null) {
            startOnLoadAnimation()
        }
    }
}

private const val TRANSITION_DURATION = 300L
