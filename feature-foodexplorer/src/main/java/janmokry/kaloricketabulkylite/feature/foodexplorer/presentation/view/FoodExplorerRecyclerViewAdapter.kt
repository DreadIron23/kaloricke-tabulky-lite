package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.commit451.coiltransformations.CropTransformation
import janmokry.kaloricketabulkylite.core.ui.R
import janmokry.kaloricketabulkylite.core.ui.dpToPx
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.FoodItemBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodItemViewData

class FoodExplorerRecyclerViewAdapter
    : RecyclerView.Adapter<FoodExplorerRecyclerViewAdapter.ViewHolder>() {

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
            thumbnailImageView.context
            if (item.imageThumbnailUrl.isNotBlank()) {
                thumbnailImageView.load(item.imageThumbnailUrl) {
                    placeholder(R.drawable.image_placeholder_56)
                    transformations(
                        CropTransformation(),
                        RoundedCornersTransformation(thumbnailImageView.context.dpToPx(8))
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val headlineTextView = binding.headline
        val trailingTopTextView = binding.trailingSupportingTextTop
        val trailingBottomTextView = binding.trailingSupportingTextBottom
        val thumbnailImageView = binding.imageView

    }

}