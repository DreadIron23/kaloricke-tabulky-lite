package janmokry.kaloricketabulkylite.feature.foodexplorer.repository.api

import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.FoodDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDetailApi {

    @GET("getPotravina.php")
    suspend fun getFoodDetail(@Query("GUID_Potravina") guidFood: String): Result<FoodDetailDto>
}