package nam.zuchu.patternproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.*

@Database(
    entities = [
        BorrowCard::class,
        Book::class,
        LibraryManager::class,
        Member::class,
        TypeOfBook::class
    ], exportSchema = false, version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun useUserDAO(): AppDAO

    companion object {
        private const val DB_NAME = "User Database"

        @Volatile
        var INSTANCE: AppDatabase? = null
        fun useDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}