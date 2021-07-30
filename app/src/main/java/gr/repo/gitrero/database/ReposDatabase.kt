package gr.repo.gitrero.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ReposEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ReposTypeConverter::class)
abstract class ReposDatabase: RoomDatabase() {

    abstract fun reposDao(): ReposDao

}