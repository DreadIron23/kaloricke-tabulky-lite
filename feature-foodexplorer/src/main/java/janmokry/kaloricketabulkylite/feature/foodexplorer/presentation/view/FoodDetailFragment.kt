package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import janmokry.kaloricketabulkylite.feature.foodexplorer.R
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.FragmentFoodDetailBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodDetailViewModel
import com.google.android.material.R as MaterialR

class FoodDetailFragment : Fragment(R.layout.fragment_food_detail) {

    private val binding by viewBinding(FragmentFoodDetailBinding::bind)

    private val viewModel: FoodDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewTransitions(view)
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
}