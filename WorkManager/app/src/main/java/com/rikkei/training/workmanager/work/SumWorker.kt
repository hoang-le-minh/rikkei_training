package com.rikkei.training.workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.rikkei.training.workmanager.*

class SumWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Handling a + b", applicationContext)
        sleep()
        val numA = inputData.getInt(NUM_A, 0)
        val numB = inputData.getInt(NUM_B, 0)

        return try {
            val outputData = workDataOf(SUMMATION to numA+numB)
            makeStatusNotification("Complete! a + b = ${numA+numB}", applicationContext)
            Result.success(outputData)
        } catch (e: Exception){
            Result.failure()
        }
    }

}