package janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data

data class FoodDetailData(
    val name: String,
    val values: NutritionData,
    val units: List<UnitData>,
    val contentDescription: String,
    val healthDescription: String,
    val vitaminChips: List<ChipData>,
    val mineralChips: List<ChipData>,
    val healthChips: List<ChipData>,
    val photo: String?,
    val photoThumb: String?,
    val url: String?,
    val gastroPartner: String?,
    val guidFood: String,
)

data class NutritionData (
    val energy: NutritionDetailData,
    val proteins: NutritionDetailData,
    val carbohydrates: NutritionDetailData,
    val sugars: NutritionDetailData,
    val fats: NutritionDetailData,
    val saturatedFattyAcids: NutritionDetailData,
    val transFattyAcids: NutritionDetailData,
    val cholesterol: NutritionDetailData,
    val fiber: NutritionDetailData,
    val sodium: NutritionDetailData,
    val calcium: NutritionDetailData,
    val salt: NutritionDetailData,
    val water: NutritionDetailData,
    val monounsaturated: NutritionDetailData,
    val polyunsaturated: NutritionDetailData,
)

data class NutritionDetailData (
    val unit: String,
    val value: Double,
)

data class UnitData (
    val amount: Double,
    val text: String,
)

data class ChipData (
    val name: String,
    val description: String,
)
