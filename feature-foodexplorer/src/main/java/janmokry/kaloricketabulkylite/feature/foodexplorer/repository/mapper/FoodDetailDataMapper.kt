package janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.ChipData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodDetailData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.UnitData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.NutritionDetailData
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.NutritionData
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.FoodDetailDto
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.Hodnoty
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.Jednotka
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.Stitek
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.Stitky
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.UnitPair
import javax.inject.Inject

interface FoodDetailDataMapper {

    fun mapToData(dto: FoodDetailDto): FoodDetailData
}

class FoodDetailDataMapperImpl @Inject constructor() : FoodDetailDataMapper {

    override fun mapToData(dto: FoodDetailDto): FoodDetailData = with(dto) {
        FoodDetailData(
            name = nazev,
            values = hodnoty.toData(),
            units = jednotky.jednotka.map(::toData),
            contentDescription = popisObsah,
            healthDescription = popisZdravi,
            vitaminChips = stitkyVitaminy.toData(),
            mineralChips = stitkyMineraly.toData(),
            healthChips = stitkyZdravi.toData(),
            photo = foto,
            photoThumb = fotoThumb,
            url = url,
            gastroPartner = gastroPartner,
            guidFood = guidPotravina,
        )
    }
}

private fun Hodnoty.toData(): NutritionData =
    NutritionData(
        energy = energie.toData(),
        proteins = bilkoviny.toData(),
        carbohydrates = sacharidy.toData(),
        sugars = cukry.toData(),
        fats = tuky.toData(),
        saturatedFattyAcids = nasyceneMastneKyseliny.toData(),
        transFattyAcids = transmastneKyseliny.toData(),
        cholesterol = cholesterol.toData(),
        fiber = vlaknina.toData(),
        sodium = sodik.toData(),
        calcium = vapnik.toData(),
        salt = sul.toData(),
        water = voda.toData(),
        monounsaturated = mononenasycene.toData(),
        polyunsaturated = polynenasycene.toData(),
    )

private fun UnitPair.toData(): NutritionDetailData =
    NutritionDetailData(
        unit = jedn,
        value = text.takeUnless { it.isBlank() }?.toDouble() ?: 0.0,
    )

private fun toData(jednotka: Jednotka): UnitData = with(jednotka) {
    UnitData(
        amount = nasobek.toDouble(),
        text = text,
    )
}

private fun Stitky?.toData(): List<ChipData> =
    this?.stitek?.map(::toData) ?: emptyList()

private fun toData(stitek: Stitek): ChipData = with(stitek) {
    ChipData(
        name = nazev,
        description = text,
    )
}
