package gr.repo.gitrero.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gr.repo.gitrero.models.commits.Commits
import gr.repo.gitrero.models.commits.CommitsItem
import java.lang.reflect.Type

class ReposTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun commitToString(commit: Commits): String {
        return gson.toJson(commit)
    }

    @TypeConverter
    fun stringToCommit(data: String): Commits {
        val listType = object : TypeToken<Commits>() {}.type
        return gson.fromJson(data, listType)
    }
}