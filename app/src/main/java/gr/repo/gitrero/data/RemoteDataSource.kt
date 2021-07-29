package gr.repo.gitrero.data

import gr.repo.gitrero.models.commits.Commits
import gr.repo.gitrero.models.repository.Repo
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val gitRepoApi: GitRepoApi
) {

    suspend fun getRepositories(owner: String, repo: String): Response<Repo>{
        return gitRepoApi.getRepositories(owner, repo)
    }

    suspend fun getCommits(owner: String, repo: String, per_page: Int, page: Int): Response<Commits>{
        return gitRepoApi.getCommits(owner, repo, per_page, page)
    }

}