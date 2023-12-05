package com.rikkei.training.workmanager

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.rikkei.training.workmanager.work.CalcWorker
import com.rikkei.training.workmanager.work.SumWorker

class CalcViewModel(application: Application): ViewModel() {

    internal var result: Int = 0
    private val workManager = WorkManager.getInstance(application)

    internal var outputWorkInfo: LiveData<List<WorkInfo>>

    init {
        outputWorkInfo = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }

    internal fun add(numA: Int, numB: Int){
        val data = Data.Builder().putInt(NUM_A, numA).putInt(NUM_B, numB).build()

        val numRequest = OneTimeWorkRequestBuilder<CalcWorker>().setInputData(data).build()


//        var continuation = workManager.beginWith(numRequest)
        val sum = OneTimeWorkRequestBuilder<SumWorker>().addTag(TAG_OUTPUT).build()
//        continuation = continuation.then(sum)

//        continuation.enqueue()
        workManager.beginUniqueWork(SUMMATION_WORK_NAME,ExistingWorkPolicy.REPLACE , numRequest).then(sum).enqueue()
    }

    internal fun cancelWork(){
        workManager.cancelUniqueWork(SUMMATION_WORK_NAME)
    }

    class CalcViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CalcViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CalcViewModel(application) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}