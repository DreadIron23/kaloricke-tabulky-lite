package janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.Food
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodListData
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.FoodListDto
import javax.inject.Inject

interface FoodListDataMapper {

    fun mapToData(dto: FoodListDto): FoodListData
}

class FoodListDataMapperImpl @Inject constructor() : FoodListDataMapper {

    override fun mapToData(dto: FoodListDto): FoodListData = dto.map()

    private fun FoodListDto.map(): FoodListData = when (potravina) {
        null -> if (code == 404) FoodListData.NotFound else FoodListData.Error
        else ->
            FoodListData.Data(
                foodList = potravina.map {
                    Food(
                        name = it.nazev,
                        favourite = it.oblibena,
                        author = it.autor,
                        photo = it.foto,
                        photoThumb = it.fotoThumb,
                        energyUnit = it.energie?.jedn ?: "",
                        energyValue = it.energie?.energie ?: "",
                        guidFood = it.guidPotravina,
                        idState = it.idStav,
                        lock = it.zamek,
                        category = it.kategorie,
                        isDrink = it.napoj,
                    )
                }
            )

        }
}