package gr.repo.gitrero

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job

@HiltAndroidApp
class MyApplication: Application() {

    companion object{
        val scope = GlobalScope // could also use an other scope such as viewModelScope if available
        var job: Job? = null
    }

}