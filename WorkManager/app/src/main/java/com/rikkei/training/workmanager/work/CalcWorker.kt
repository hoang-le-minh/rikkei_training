package com.rikkei.training.workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.rikkei.training.workmanager.NUM_A
import com.rikkei.training.workmanager.NUM_B
import com.rikkei.training.workmanager.makeStatusNotification
import com.rikkei.training.workmanager.sleep

class CalcWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Sum of num a and num b", applicationContext)
        sleep()

        val numA = inputData.getInt(NUM_A, 0)
        val numB = inputData.getInt(NUM_B, 0)

        return try {
            val outputData = workDataOf(NUM_A to numA, NUM_B to numB)
            makeStatusNotification("Complete! a and b", applicationContext)
            Result.success(outputData)
        } catch (e: Exception){
            Result.failure()
        }
    }


}