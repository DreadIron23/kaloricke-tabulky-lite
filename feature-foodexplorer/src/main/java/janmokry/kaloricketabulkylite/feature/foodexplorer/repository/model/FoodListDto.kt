package janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class FoodListDto (
    @Element
    val potravina: List<Potravina>?,

    @Attribute
    val jedn: String?,

    @Attribute(name = "cas_zpracovani")
    val casZpracovani: String?,

    @PropertyElement
    val code: Int?,

    @PropertyElement
    val msg: String?
)

@Xml
data class Potravina (

    @PropertyElement
    val nazev: String,

    @PropertyElement
    val oblibena: Boolean,

    @PropertyElement
    val autor: String?,

    @PropertyElement
    val foto: String,

    @PropertyElement(name ="foto_thumb")
    val fotoThumb: String,

    @Element
    val energie: Energie?,

    @Attribute(name = "guid_potravina")
    val guidPotravina: String,

    @Attribute(name = "id_stav")
    val idStav: String? = null,

    @Attribute(name = "zamek")
    val zamek: String,

    @Attribute(name = "kategorie")
    val kategorie: String,

    @Attribute
    val napoj: Boolean
)

@Xml
data class Energie (
    @Attribute
    val jedn: String,

    @TextContent
    val energie: String
)