package gr.repo.gitrero.data

import gr.repo.gitrero.models.commits.Commits
import gr.repo.gitrero.models.repository.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitRepoApi {

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepositories(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Repo>

    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<Commits>

}