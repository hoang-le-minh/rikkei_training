package com.rikkei.training.thread

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.rikkei.training.thread.databinding.ActivitySlideImageBinding
import java.net.URL
import java.util.*

class SlideImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySlideImageBinding
    private var currentPosition = 0

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlideImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            doNextImage()
        }

        binding.btnPrev.setOnClickListener {
            doPrevImage()
        }

        imageTask()

        binding.cbAuto.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                doAutoLoadImage()
                binding.btnPrev.isEnabled = false
                binding.btnNext.isEnabled = false
            } else {
                binding.btnPrev.isEnabled = true
                binding.btnNext.isEnabled = true
                timer?.cancel()
            }
        }
    }

    private fun doPrevImage() {
        if(currentPosition == 0){
            currentPosition = listUrlImage.size - 1
        } else {
            --currentPosition
        }
        imageTask()
    }

    private fun doNextImage() {
        if(currentPosition == listUrlImage.size - 1){
            currentPosition = 0
        } else {
            ++currentPosition
        }
        imageTask()
    }

    private fun doAutoLoadImage(){
        timerTask = object: TimerTask(){
            override fun run() {
                runOnUiThread {
                    ++currentPosition
                    if(currentPosition == listUrlImage.size){
                        currentPosition = 0
                    }
                    imageTask()
                }
            }

        }
        timer = Timer()
        timer?.schedule(timerTask, 0, 5000)

    }

    private fun imageTask(){
        lifecycleScope.executeAsyncTask(onPreExecute = {
            updateUiProgressBar(1)
        }, doInBackground = { _ :  suspend (progress: Int) -> Unit ->
            Thread.sleep(500)
            var bitmap: Bitmap
            try {
                val url = URL(listUrlImage[currentPosition])
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                bitmap
            } catch (e: Exception){
                bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                e.printStackTrace()
                bitmap
            }
        }, onProgressUpdate = {

        }, onPostExecute = {
            binding.imageView.setImageBitmap(it)
            updateUiProgressBar(0)
        })
    }

    // 1: show progressBar, hide imageView || 2: hide progressBar, show imageView
    private fun updateUiProgressBar(action: Int){
        when(action){
            1 -> {
                binding.imageView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.btnPrev.isEnabled = false
                binding.btnNext.isEnabled = false
            }
            0 -> {
                binding.imageView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.btnPrev.isEnabled = true
                binding.btnNext.isEnabled = true
            }
        }
    }

    private var listUrlImage = mutableListOf(
        "https://cdn0.fahasa.com/media/catalog/product/b/_/b_a-1_m_t-_i-_c-m_t---copy.jpg",
        "https://cdn0.fahasa.com/media/catalog/product/b/i/bia_worldhistory_bia1.jpg",
        "https://cdn0.fahasa.com/media/catalog/product/8/9/8936213491057.jpg",
        "https://cdn0.fahasa.com/media/catalog/product/i/m/image_217480.jpg",
        "https://cdn0.fahasa.com/media/catalog/product/i/m/image_195509_1_36793.jpg"
    )

//    // =========== AsyncTask ===============
//    private fun <P, R> CoroutineScope.executeAsyncTask(
//        onPreExecute: () -> Unit,
//        doInBackground: suspend (suspend (P) -> Unit) -> R,
//        onPostExecute: (R) -> Unit,
//        onProgressUpdate: (P) -> Unit
//    ) = launch {
//        onPreExecute()
//
//        val result = withContext(Dispatchers.IO) {
//            doInBackground {
//                withContext(Dispatchers.Main) { onProgressUpdate(it) }
//            }
//        }
//        onPostExecute(result)
//    }
}