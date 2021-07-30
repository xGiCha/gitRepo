package gr.repo.gitrero.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommits(reposEntity: ReposEntity)

    @Query("SELECT * FROM repos_table WHERE id=:id")
    fun readCommits(id: String): Flow<List<ReposEntity>>


}