package com.rikkei.training.workmanager

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import com.rikkei.training.workmanager.coroutinework.ImageCompressWorker
import com.rikkei.training.workmanager.coroutinework.ImageViewModel
import com.rikkei.training.workmanager.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ResultViewModel.ResultViewModelFactory(application)
        )[ResultViewModel::class.java]
    }

    private val imageViewModel by lazy {
        ViewModelProvider(this, ImageViewModel.ImageViewModelFactory(application))[ImageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("hoangminhdh11", "onCreate: ")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnCancel.setOnClickListener {
                viewModel.cancelWork()
            }

            btnHandling.setOnClickListener {
                var numA = edtNumA.text.toString()
                var numB = edtNumB.text.toString()
                if(numA == "") numA = "0"
                if(numB == "") numB = "0"
                viewModel.add(numA.toInt(), numB.toInt())

            }

            btnSeeResult.setOnClickListener {
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(RESULT, viewModel.result)
                startActivity(intent)

            }
            btnClearImage.setOnClickListener {
                clearCacheImage()
                Log.d("hoangminhdh11", "onCreate: clear cache image")
            }
        }

        // sum view model
        viewModel.outputWorkInfo.observe(this){
            if(it.isNullOrEmpty()){
                return@observe
            }
            val workInfo = it[0]
            if(workInfo.state.isFinished){
                showWorkFinished()
                val result = workInfo.outputData.getInt(SUMMATION, -1)
                if(result != -1){
                    binding.btnSeeResult.visibility = View.VISIBLE
                    viewModel.result = result
                }
            } else {
                showWorkInProgress()
            }
        }


//        // image view model
//        imageViewModel.outputWorkInfo?.observe(this){ it ->
//            val workResult = it?.outputData
//            if (workResult != null){
//                val filePath = workResult.getString(RESULT_PATH)
//                filePath?.let { path ->
//                    val bitmap = BitmapFactory.decodeFile(path)
//                    imageViewModel.compressBitmap = bitmap
//                }
//            }
//
//            imageViewModel.compressBitmap?.let { bitmap ->
//                binding.compressImage.setImageBitmap(bitmap)
//            }
//            imageViewModel.uncompressedUri?.let { uri ->
//                binding.uncompressImage.setImageURI(uri)
//            }
//        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("hoangminhdh11", "onNewIntent: ")
        val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        } ?: return
        imageViewModel.uncompressedUri = uri

        imageViewModel.compressImage(uri)

        // image view model
        imageViewModel.outputWorkInfo?.observe(this){ it ->
            val workResult = it?.outputData
            if (workResult != null){
                val filePath = workResult.getString(RESULT_PATH)
                filePath?.let { path ->
                    val bitmap = BitmapFactory.decodeFile(path)
                    imageViewModel.compressBitmap = bitmap
                }
            }

            imageViewModel.compressBitmap?.let { bitmap ->
                binding.compressImage.setImageBitmap(bitmap)
            }
            imageViewModel.uncompressedUri?.let { uri ->
                binding.uncompressImage.setImageURI(uri)
            }
        }

        binding.btnClearImage.setOnClickListener {
            clearCacheImage()
            Log.d("hoangminhdh11", "onNewIntent: clear cache image")
        }
    }

    private fun clearCacheImage(){
        imageViewModel.outputWorkInfo?.observe(this) { it ->
            val workResult = it?.outputData
            if (workResult != null) {
                val fileName = workResult.getString(RESULT_FILE_NAME)
                val file = fileName?.let { it1 -> File(applicationContext.cacheDir, it1) }
                if (file != null) {
                    if(file.exists()){
                        file.delete()
                        Log.d("hoangminhdh11", "clearCacheImage: $fileName")
                    }
                }

            }
        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            btnHandling.visibility = View.GONE
            btnCancel.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            btnSeeResult.visibility = View.GONE
        }
    }

    private fun showWorkFinished() {
        with(binding) {
            btnHandling.visibility = View.VISIBLE
            btnCancel.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }
}