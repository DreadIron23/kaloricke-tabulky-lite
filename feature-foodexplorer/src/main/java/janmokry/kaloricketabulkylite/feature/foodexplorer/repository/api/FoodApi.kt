package janmokry.kaloricketabulkylite.feature.foodexplorer.repository.api

import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.model.FoodListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("getSearchPotraviny.php")
    suspend fun getFoodList(@Query("Q") query: String?): Result<FoodListDto>
}