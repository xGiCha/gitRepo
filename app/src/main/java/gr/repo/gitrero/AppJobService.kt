package gr.repo.gitrero

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class AppJobService: JobService() {

    private val TAG = "Jobbbbb"
    private var jobCancelled = false
    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "job started")
        doBackgroundWork(p0)
        return true
    }

//    private fun doBackgroundWork(p0: JobParameters?) {
//        Thread(object : Runnable {
//            override fun run() {
//                if (jobCancelled)
//                    return
//                Log.d(TAG, "well done")
//                jobFinished(p0, false)
//            }
//
//        }).start()
//    }

    private fun doBackgroundWork(params: JobParameters?) {
        Thread(Runnable {
            for (i in 0..9) {
                Log.d(TAG, "run: $i")
                if (jobCancelled) {
                    return@Runnable
                }
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            Log.d(TAG, "Job finished")
            jobFinished(params, false)
        }).start()
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "job cancelled")
        jobCancelled = false
        return true
    }
}