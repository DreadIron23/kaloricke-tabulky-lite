package janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data

sealed interface FoodListData {

    object NotFound : FoodListData

    object Error : FoodListData

    data class Data(
        val foodList: List<Food>,
    ) : FoodListData
}

data class Food(

    val name: String,

    val favourite: Boolean,

    val author: String?,

    val photo: String,

    val photoThumb: String,

    val energyUnit: String,

    val energyValue: String,

    val guidFood: String,

    val idState: String? = null,

    val lock: String,

    val category: String,

    val isDrink: Boolean,
)