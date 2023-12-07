package com.rikkei.training.workmanager.coroutinework

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.rikkei.training.workmanager.*
import java.util.UUID

class ImageViewModel(application: Application): ViewModel() {
    private val workManager = WorkManager.getInstance(application)

    internal var uncompressedUri: Uri? = null
    internal var workId: UUID? = null
    internal var compressBitmap: Bitmap? = null
    internal var outputWorkInfo: LiveData<WorkInfo>? = null

    internal fun compressImage(uri: Uri){
        val data = Data.Builder().putString(IMAGE_URI, uri.toString()).putLong(IMAGE_COMPRESSION, 1024*20L).build()
        val request = OneTimeWorkRequestBuilder<ImageCompressWorker>()
            .setInputData(data)
            .setConstraints(Constraints(requiresStorageNotLow = true))
            .build()
        workId = request.id
        workId?.let {
            outputWorkInfo = workManager.getWorkInfoByIdLiveData(it)
        }
        workManager.enqueue(request)
    }


    class ImageViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ImageViewModel(application) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}