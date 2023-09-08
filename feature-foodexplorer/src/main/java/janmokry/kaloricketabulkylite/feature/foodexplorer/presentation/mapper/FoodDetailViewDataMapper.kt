package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper

import androidx.annotation.StringRes
import janmokry.kaloricketabulkylite.feature.foodexplorer.R
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.ChipData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodDetailData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.NutritionData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.NutritionDetailData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.UnitData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.ChipViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodDetailViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.NutritionDetailViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.NutritionViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.QuantityViewData
import javax.inject.Inject

interface FoodDetailViewDataMapper {

    fun mapToViewData(data: FoodDetailData): FoodDetailViewData
}

class FoodDetailViewDataMapperImpl @Inject constructor() : FoodDetailViewDataMapper {

    override fun mapToViewData(data: FoodDetailData): FoodDetailViewData = with(data) {
        FoodDetailViewData(
            name = name,
            values = values.toViewData(),
            quantity = units.map(::toViewData),
            contentDescription = contentDescription,
            healthDescription = healthDescription,
            vitaminChips = vitaminChips.map(::toViewData),
            mineralChips = mineralChips.map(::toViewData),
            healthChips = healthChips.map(::toViewData),
            photo = photo ?: "",
        )
    }

}

private fun NutritionData.toViewData(): NutritionViewData =
    NutritionViewData(
        energy = energy.toViewData(R.string.nutrition_energy, true),
        proteins = proteins.toViewData(R.string.nutrition_proteins),
        carbohydrates = carbohydrates.toViewData(R.string.nutrition_carbohydrates),
        sugars = sugars.toViewData(R.string.nutrition_sugars),
        fats = fats.toViewData(R.string.nutrition_fats),
        saturatedFattyAcids = saturatedFattyAcids.toViewData(
            R.string.nutrition_saturated_fatty_acids
        ),
        transFattyAcids = transFattyAcids.toViewData(R.string.nutrition_trans_fatty_acids),
        cholesterol = cholesterol.toViewData(R.string.nutrition_cholesterol),
        fiber = fiber.toViewData(R.string.nutrition_fiber),
        sodium = sodium.toViewData(R.string.nutrition_sodium),
        calcium = calcium.toViewData(R.string.nutrition_calcium),
        salt = salt.toViewData(R.string.nutrition_salt),
        water = water.toViewData(R.string.nutrition_water),
        monounsaturated = monounsaturated.toViewData(R.string.nutrition_monounsaturated),
        polyunsaturated = polyunsaturated.toViewData(R.string.nutrition_polyunsaturated),
    )

private fun NutritionDetailData.toViewData(
    @StringRes nameRes: Int,
    hasProminentStyle: Boolean = false,
): NutritionDetailViewData =
    NutritionDetailViewData(
        nameRes = nameRes,
        hasProminentStyle = hasProminentStyle,
        unit = unit,
        value = value,
    )

private fun toViewData(data: UnitData): QuantityViewData = with(data) {
    QuantityViewData(
        value = amount,
        text = text,
    )
}

private fun toViewData(data: ChipData): ChipViewData = with(data) {
    ChipViewData(
        name = name,
        description = description,
    )
}