package janmokry.kaloricketabulkylite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FoodExplorer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodExplorerDao(): FoodExplorerDao
}
