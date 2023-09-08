package janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class FoodDetailDto (

    @PropertyElement
    val nazev: String,

    @Element
    val hodnoty: Hodnoty,

    @Element
    val jednotky: Jednotky,

    @PropertyElement
    val popisObsah: String,

    @PropertyElement
    val popisZdravi: String,

    @Element(name = "stitkyVitaminy")
    val stitkyVitaminy: Stitky?,

    @Element(name = "stitkyMineraly")
    val stitkyMineraly: Stitky?,

    @Element(name = "stitkyZdravi")
    val stitkyZdravi: Stitky?,

    @PropertyElement
    val foto: String?,

    @PropertyElement(name = "foto_thumb")
    val fotoThumb: String?,

    @PropertyElement
    val url: String?,

    @PropertyElement(name = "gastro_partner")
    val gastroPartner: String?,

    @Attribute(name = "guid_potravina")
    val guidPotravina: String,
)

@Xml
data class Hodnoty (

    @Element(name = "energie")
    val energie: UnitPair,

    @Element(name = "bilkoviny")
    val bilkoviny: UnitPair,

    @Element(name = "sacharidy")
    val sacharidy: UnitPair,

    @Element(name = "cukry")
    val cukry: UnitPair,

    @Element(name = "tuky")
    val tuky: UnitPair,

    @Element(name = "nasyceneMastneKyseliny")
    val nasyceneMastneKyseliny: UnitPair,

    @Element(name = "transmastneKyseliny")
    val transmastneKyseliny: UnitPair,

    @Element(name = "cholesterol")
    val cholesterol: UnitPair,

    @Element(name = "vlaknina")
    val vlaknina: UnitPair,

    @Element(name = "sodik")
    val sodik: UnitPair,

    @Element(name = "vapnik")
    val vapnik: UnitPair,

    @Element(name = "sul")
    val sul: UnitPair,

    @Element(name = "voda")
    val voda: UnitPair,

    @Element(name = "mononenasycene")
    val mononenasycene: UnitPair,

    @Element(name = "polynenasycene")
    val polynenasycene: UnitPair,
)

@Xml
data class UnitPair (

    @Attribute
    val jedn: String,

    @TextContent
    val text: String,
)

@Xml
data class Jednotky (

    @Element
    val jednotka: List<Jednotka>,
)

@Xml
data class Jednotka (

    @Attribute
    val nasobek: String,

    @Attribute(name = "guid_jednotka")
    val guidJednotka: String,

    @TextContent
    val text: String,
)

@Xml
data class Stitky (

    @Element
    val stitek: List<Stitek>?,
)

@Xml
data class Stitek (

    @Attribute
    val nazev: String,

    @Attribute(name = "guid_stitek")
    val guidStitek: String,

    @Attribute
    val typ: String,

    @TextContent
    val text: String,
)
