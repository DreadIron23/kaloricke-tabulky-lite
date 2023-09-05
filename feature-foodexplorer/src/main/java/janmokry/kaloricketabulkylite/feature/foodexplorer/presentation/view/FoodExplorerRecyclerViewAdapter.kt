package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.commit451.coiltransformations.CropTransformation
import janmokry.kaloricketabulkylite.core.ui.dpToPx
import janmokry.kaloricketabulkylite.feature.foodexplorer.R
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.FoodItemBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodItemViewData

class FoodExplorerRecyclerViewAdapter(
    private val onNavigateToDetailCallback: () -> Unit,
) : RecyclerView.Adapter<FoodExplorerRecyclerViewAdapter.ViewHolder>() {

    private var values: List<FoodItemViewData> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun feedItems(newItems: List<FoodItemViewData>) {
        values = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            headlineTextView.text = item.name
            trailingTopTextView.text = item.energyValue
            trailingBottomTextView.text = item.energyUnit
            if (item.imageThumbnailUrl.isNotBlank()) {
                thumbnailImageView.load(item.imageThumbnailUrl) {
                    placeholder(R.drawable.image_placeholder_56)
                    transformations(
                        CropTransformation(),
                        RoundedCornersTransformation(thumbnailImageView.context.dpToPx(8))
                    )
                }
            }

            with(surfaceLayout) {
                transitionName = resources.getString(R.string.food_item_transition_name, item.guid)

                setOnClickListener { view ->
                    onNavigateToDetailCallback()

                    val endTransition = view.resources.getString(R.string.food_detail_transition_name)
                    val extras = FragmentNavigatorExtras(view to endTransition)
                    view.findNavController()
                        .navigate(
                            FoodExplorerFragmentDirections
                                .actionFoodExplorerFragmentToFoodDetailFragment(item.guid),
                            extras,
                        )
                }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val surfaceLayout = binding.surface
        val headlineTextView = binding.headline
        val trailingTopTextView = binding.trailingSupportingTextTop
        val trailingBottomTextView = binding.trailingSupportingTextBottom
        val thumbnailImageView = binding.imageView

    }
}
