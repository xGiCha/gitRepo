package gr.repo.gitrero.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import gr.repo.gitrero.data.Repository
import gr.repo.gitrero.models.commits.Commits
import gr.repo.gitrero.models.repository.Repo
import gr.repo.gitrero.util.NetworkResult
import gr.repo.gitrero.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    /** RETROFIT */
    var repos: SingleLiveEvent<NetworkResult<Repo>> = SingleLiveEvent()
    var commits: SingleLiveEvent<NetworkResult<Commits>> = SingleLiveEvent()

//    private var movieList: LiveData<PagedList<Movie>>? = null

    fun getRepositories(owner: String, repo: String){
        viewModelScope.launch(Dispatchers.Default) {
            runCatching {
                repository.remote.getRepositories(owner, repo)
            }.onFailure {
                it.printStackTrace()
            }.onSuccess {response ->
                if(response.isSuccessful){
                    response.body()?.let {
                        repos.postValue(NetworkResult.Success(it))
                    }
                }else{
                    repos.postValue(NetworkResult.Error("something went wrong"))
                }
            }
        }
    }

    fun getCommits(owner: String, repo: String){
        viewModelScope.launch(Dispatchers.Default) {
            runCatching {
                repository.remote.getCommits(owner, repo, 20, 1)
            }.onFailure {
                it.printStackTrace()
            }.onSuccess {response ->
                if(response.isSuccessful){
                    response.body()?.let {
                        commits.postValue(NetworkResult.Success(it))
                    }
                }else{
                    repos.postValue(NetworkResult.Error("something went wrong"))
                }
            }
        }
    }

//    private val config = PagedList.Config.Builder()
//        .setPageSize(20)
//        .setInitialLoadSizeHint(20)
//        .setEnablePlaceholders(false)
//        .build()
//
//    fun getMovieList(mode: Int, searchQuery: String):  LiveData<PagedList<Movie>>?{
//        movieList = initializedPagedListBuilder(config, mode, searchQuery).build()
//        return movieList
//    }
//
//    private fun initializedPagedListBuilder(config: PagedList.Config, mode: Int, searchQuery: String): LivePagedListBuilder<Int, Movie> {
//        val dataSourceFactory = MoviesDataSourceFactory(viewModelScope, repository, mode, searchQuery)
//        return LivePagedListBuilder<Int, Movie>(dataSourceFactory, config)
//    }
//
//    /** ROOM DATABASE */
//    val readMovies: LiveData<List<MoviesEntity>> = repository.local.readMovies().asLiveData()
//
//    fun insertMovies(moviesEntity: MoviesEntity) =
//            viewModelScope.launch(Dispatchers.IO) {
//                repository.local.insertMovies(moviesEntity)
//            }
//
//    fun deleteMovie(moviesEntity: MoviesEntity) =
//            viewModelScope.launch(Dispatchers.IO) {
//                repository.local.deleteMovie(moviesEntity)
//            }
}