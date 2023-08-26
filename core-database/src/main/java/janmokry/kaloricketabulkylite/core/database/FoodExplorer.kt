package janmokry.kaloricketabulkylite.core.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class FoodExplorer(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface FoodExplorerDao {
    @Query("SELECT * FROM foodexplorer ORDER BY uid DESC LIMIT 10")
    fun getFoodExplorers(): Flow<List<FoodExplorer>>

    @Insert
    suspend fun insertFoodExplorer(item: FoodExplorer)
}
