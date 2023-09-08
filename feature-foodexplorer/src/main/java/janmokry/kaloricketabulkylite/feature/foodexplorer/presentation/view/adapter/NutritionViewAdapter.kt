package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.NutrientItemBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.databinding.NutrientItemProminentBinding
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.NutritionDetailViewData
import java.math.RoundingMode
import java.text.DecimalFormat

class NutritionViewAdapter(layoutProvider: () -> ViewGroup) {

    private val layout: ViewGroup by lazy { layoutProvider() }

    private var values: List<NutritionDetailViewData> = emptyList()

    private val regularDecimalFormatter = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.DOWN
    }
    private val prominentDecimalFormatter = DecimalFormat("#").apply {
        roundingMode = RoundingMode.DOWN
    }

    /**
     * Holds added [ViewHolder]s to [layout] identified by their [StringRes] id.
     *
     * View removal is not supported - we don't need that because data are fetched from BE only
     * once.
     */
    private val addedViews = mutableMapOf<Int, ViewHolder>()

    fun feedData(viewData: List<NutritionDetailViewData>) {
        values = viewData

        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        values.forEach { viewData ->
            val maybeView = addedViews[viewData.nameRes]

            val view = maybeView ?: createAndAddViewHolder(viewData)

            onBindViewHolder(view, viewData)
        }
    }

    private fun createAndAddViewHolder(viewData: NutritionDetailViewData) : ViewHolder =
        onCreateViewHolder(layout, viewData.hasProminentStyle)
            .also { viewHolder ->
                addedViews[viewData.nameRes] = viewHolder
                layout.addView(viewHolder.rootView)
            }

    private fun onCreateViewHolder(parent: ViewGroup, isProminent: Boolean): ViewHolder =
        if (isProminent) {
            NutrientItemProminentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
                .let { NutrientBinding.Prominent(it) }
        } else {
                NutrientItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                    .let { NutrientBinding.Regular(it) }
        }
            .let { ViewHolder(it) }

    private fun onBindViewHolder(holder: ViewHolder, viewData: NutritionDetailViewData) {
        holder.apply {
            headlineTextView.text = holder.rootView.resources.getString(viewData.nameRes)
            trailingTextView.text = with(viewData) {
                if (value == 0.0) {
                    "—"
                } else {
                    "${(value).format(this)} $unit"
                }
            }
        }
    }

    private fun Double.format(viewData: NutritionDetailViewData): String {
        val formatter = if (viewData.hasProminentStyle) {
            prominentDecimalFormatter
        } else {
            regularDecimalFormatter
        }
        return formatter.format(this)
    }
}

private class ViewHolder(binding: NutrientBinding) {
    val rootView = binding.viewRoot
    val headlineTextView: TextView = binding.headline
    val trailingTextView: TextView = binding.trailingText

}

private sealed interface NutrientBinding {
    val viewRoot: View
    val headline: TextView
    val trailingText: TextView

    class Regular(binding: NutrientItemBinding) : NutrientBinding {
        override val viewRoot = binding.root
        override val headline = binding.headline
        override val trailingText = binding.trailingText
    }

    class Prominent(binding: NutrientItemProminentBinding) : NutrientBinding {
        override val viewRoot = binding.root
        override val headline = binding.headline
        override val trailingText = binding.trailingText
    }
}