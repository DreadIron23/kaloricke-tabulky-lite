package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata

import androidx.annotation.StringRes

data class FoodDetailViewData(
    val name: String,
    val values: NutritionViewData,
    val quantity: List<QuantityViewData>,
    val contentDescription: String,
    val healthDescription: String,
    val vitaminChips: List<ChipViewData>,
    val mineralChips: List<ChipViewData>,
    val healthChips: List<ChipViewData>,
    val photo: String,
)

data class NutritionViewData(
    val energy: NutritionDetailViewData,
    val proteins: NutritionDetailViewData,
    val carbohydrates: NutritionDetailViewData,
    val sugars: NutritionDetailViewData,
    val fats: NutritionDetailViewData,
    val saturatedFattyAcids: NutritionDetailViewData,
    val transFattyAcids: NutritionDetailViewData,
    val cholesterol: NutritionDetailViewData,
    val fiber: NutritionDetailViewData,
    val sodium: NutritionDetailViewData,
    val calcium: NutritionDetailViewData,
    val salt: NutritionDetailViewData,
    val water: NutritionDetailViewData,
    val monounsaturated: NutritionDetailViewData,
    val polyunsaturated: NutritionDetailViewData,
)

data class NutritionDetailViewData(
    @StringRes val nameRes: Int,
    val hasProminentStyle: Boolean,
    val unit: String,
    val value: Double,
)

data class QuantityViewData(
    val value: Double,
    val text: String,
)

data class ChipViewData(
    val name: String,
    val description: String,
)

fun FoodDetailViewData.multiplyBy(multiplier: Double): FoodDetailViewData =
    copy(values = values.multiplyBy(multiplier))

fun NutritionViewData.multiplyBy(multiplier: Double): NutritionViewData =
    NutritionViewData(
        energy = energy.multiplyBy(multiplier),
        proteins = proteins.multiplyBy(multiplier),
        carbohydrates = carbohydrates.multiplyBy(multiplier),
        sugars = sugars.multiplyBy(multiplier),
        fats = fats.multiplyBy(multiplier),
        saturatedFattyAcids = saturatedFattyAcids.multiplyBy(multiplier),
        transFattyAcids = transFattyAcids.multiplyBy(multiplier),
        cholesterol = cholesterol.multiplyBy(multiplier),
        fiber = fiber.multiplyBy(multiplier),
        sodium = sodium.multiplyBy(multiplier),
        calcium = calcium.multiplyBy(multiplier),
        salt = salt.multiplyBy(multiplier),
        water = water.multiplyBy(multiplier),
        monounsaturated = monounsaturated.multiplyBy(multiplier),
        polyunsaturated = polyunsaturated.multiplyBy(multiplier),
    )

fun NutritionDetailViewData.multiplyBy(multiplier: Double): NutritionDetailViewData =
    copy(value = value * multiplier)