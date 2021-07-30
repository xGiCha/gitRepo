package gr.repo.gitrero.data

import gr.repo.gitrero.database.ReposDao
import gr.repo.gitrero.database.ReposEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val reposDao: ReposDao
) {

    fun readCommits(id: String): Flow<List<ReposEntity>> {
        return reposDao.readCommits(id = id)
    }

    suspend fun insertCommits(reposEntity: ReposEntity) {
        reposDao.insertCommits(reposEntity)
    }

}