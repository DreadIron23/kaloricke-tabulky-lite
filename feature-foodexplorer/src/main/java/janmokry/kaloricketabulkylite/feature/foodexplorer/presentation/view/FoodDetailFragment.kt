package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import janmokry.kaloricketabulkylite.feature.foodexplorer.R
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.FragmentFoodDetailBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.adapter.NutritionViewAdapter
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodDetailViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodDetailUiState
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.google.android.material.R as MaterialR

@AndroidEntryPoint
class FoodDetailFragment : Fragment(R.layout.fragment_food_detail) {

    private val binding by viewBinding(FragmentFoodDetailBinding::bind)

    private val viewModel: FoodDetailViewModel by viewModels()

    private val mainNutritionCardAdapter: NutritionViewAdapter by lazy {
        NutritionViewAdapter(binding::mainNutrientsCardContent)
    }

    private val otherNutritionCardAdapter: NutritionViewAdapter by lazy {
        NutritionViewAdapter(binding::otherNutrientsCardContent)
    }

    private lateinit var quantityLookupMap: Map<String, Double>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarUpNavigation()
        setupViewTransitions(view)
        setupMultiplicationSliderListener()
        setupMultiplicationNumberListener()
        setupQuantityPickerListener()

        collectUiState()
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { uiState ->
                    when (uiState) {
                        FoodDetailUiState.Loading -> Unit
                        is FoodDetailUiState.Success -> showSuccessState(uiState)
                        is FoodDetailUiState.Error -> showErrorState()
                    }
                }
        }
    }

    private fun showSuccessState(uiState: FoodDetailUiState.Success) = with(binding) {
        with(uiState.viewData) {

            if (photo.isNotBlank()) {
                detailImage.load(photo) {
                    placeholder(R.drawable.ic_image_placeholder_inset)
                }
            }

            toolbarLayout.title = name
            foodTitle.text = name

            initAndSetQuantityPicker()
            setNutritionCardData()
        }
    }

    private fun FoodDetailViewData.setNutritionCardData() {
        val mainNutritionCardData = with(values) {
            listOf(energy, proteins, carbohydrates, fats, fiber)
        }
        mainNutritionCardAdapter.feedData(mainNutritionCardData)

        val otherNutritionCardData = with(values) {
            listOf(
                sugars, saturatedFattyAcids, transFattyAcids, cholesterol, sodium, calcium,
                salt, water, monounsaturated, polyunsaturated
            )
        }
        otherNutritionCardAdapter.feedData(otherNutritionCardData)
    }

    private fun showErrorState() {
        Snackbar.make(requireView(), R.string.generic_error, Snackbar.LENGTH_LONG).show()
    }

    private fun FoodDetailViewData.initAndSetQuantityPicker() {
        initAndSetQuantityLookupMap()

        binding.quantityPickerLayout.editText!!.apply {
            if (text.isBlank()) {
                setText(quantity.firstOrNull { it.value == 100.0 }?.text ?: "")
            }
        }

        (binding.quantityPicker as MaterialAutoCompleteTextView).apply {
            setSimpleItems(quantity.map { it.text }.toTypedArray())
        }
    }

    private fun FoodDetailViewData.initAndSetQuantityLookupMap() {
        if (!::quantityLookupMap.isInitialized) {
            val mutableMap = mutableMapOf<String, Double>()

            quantity.forEach { quantity ->
                mutableMap[quantity.text] = quantity.value
            }

            quantityLookupMap = mutableMap
        }
    }

    private fun setupQuantityPickerListener() {
        binding.quantityPickerLayout.editText!!.doAfterTextChanged {  text ->
            if (text.isNullOrBlank() || !::quantityLookupMap.isInitialized) {
                return@doAfterTextChanged
            }

            text.toString().also { changedText ->
                val value = quantityLookupMap[changedText]

                if (value != null) {
                    viewModel.onQuantityChange(value)
                }
            }
        }
    }

    private fun setupMultiplicationNumberListener() {
        binding.multiplicationNumber.doAfterTextChanged { text ->
            if (text.isNullOrBlank()) return@doAfterTextChanged

            text.toString().toFloat().also { changedValue ->
                if (changedValue in 1f..10f) {
                    binding.multiplicationSlider.value = changedValue
                }

                viewModel.onMultiplierChange(changedValue.toDouble())
            }
        }
    }

    private fun setupMultiplicationSliderListener() {
        binding.multiplicationSlider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                binding.multiplicationNumber.setText(value.toInt().toString())
            }
        }
    }

    private fun setupViewTransitions(view: View) {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300L
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(
                MaterialColors.getColor(view, MaterialR.attr.colorSurface)
            )
        }
    }

    private fun setupToolbarUpNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}