package com.rikkei.training.workmanager.coroutinework

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.rikkei.training.workmanager.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

class ImageCompressWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val stringUri = inputData.getString(IMAGE_URI)
            val compressionInBytes = inputData.getLong(IMAGE_COMPRESSION, 0L)
            val imageUri = Uri.parse(stringUri)
            val bytes = applicationContext.contentResolver.openInputStream(imageUri)?.use {
                it.readBytes()
            } ?: return@withContext Result.failure()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            var outputBytes: ByteArray
            var quality = 100

            do {
                val outputStream = ByteArrayOutputStream()
                outputStream.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                    outputBytes = outputStream.toByteArray()
                    quality -= (quality * 0.1).roundToInt()
                }
            } while (quality > 5 && outputBytes.size > compressionInBytes)

            val file = File(applicationContext.cacheDir, "${id}.jpg")
            file.writeBytes(outputBytes)
            val outputData = workDataOf(RESULT_PATH to file.absolutePath, RESULT_FILE_NAME to "${id}.jpg")
            Log.d("hoangminhdh11", "doWork: ${file.absolutePath}")
            makeStatusNotification("Complete Compress Image", applicationContext)
            return@withContext Result.success(outputData)
        }

    }
}