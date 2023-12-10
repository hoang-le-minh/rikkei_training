package com.rikkei.training.thread

import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.rikkei.training.thread.databinding.ActivityAsyncTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class AsyncTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsyncTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncrease.setOnClickListener {
            doIncreaseNumber()
        }

        binding.btnDownload.setOnClickListener {
            doDownloadImage()
        }
    }

    // ================== Download Image ==========================
    private fun doDownloadImage() {
        val urlStringList = mutableListOf(
            "https://cdn0.fahasa.com/media/catalog/product/_/k/_khong-phai-soi-nhung-cung-dung-la-cuu.jpg",
            "https://cdn0.fahasa.com/media/catalog/product/m/u/muonkiepnhansinh3_khonho_bia1.jpg",
            "https://cdn0.fahasa.com/media/wysiwyg/hieu_kd/2023-08-frame/FrameNCC_DinhTi_1080x1080.png")
        lifecycleScope.executeAsyncTask(onPreExecute = {
            updateProgressBar(1)
            urlStringList.shuffle()
        }, doInBackground = { _: suspend (progress: Int) -> Unit ->
            try {
                Thread.sleep(2000)
                val url = URL(urlStringList[0])
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                bitmap
            } catch (e: Exception){
                e.printStackTrace()
                val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                bitmap
            }

        }, onProgressUpdate = {

        }, onPostExecute = {
            binding.imageView.setImageBitmap(it)
            updateProgressBar(0)
            Toast.makeText(this, "Download Done!", Toast.LENGTH_LONG).show()
        })
    }

    private fun updateProgressBar(action: Int){
        when(action){
            1 -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnDownload.visibility = View.GONE
            }
            0 -> {
                binding.progressBar.visibility = View.GONE
                binding.btnDownload.visibility = View.VISIBLE
            }
        }
    }

    // =================== Increase Number ===========================
    private fun doIncreaseNumber() {
        var count = 0
        lifecycleScope.executeAsyncTask(onPreExecute = {
            count = 0
        }, doInBackground = { publishProgress: suspend (progress: Int) -> Unit ->
            while (count < 10){
                count++
                publishProgress(count)
                Thread.sleep(1000)
            }
            publishProgress(++count)
            "Increase Done!"
        }, onPostExecute = {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }, onProgressUpdate = {
            updateUiIncrease(it)
        })
    }

    private fun updateUiIncrease(count: Int) {
        binding.txtCount.text = count.toString()
        when{
            count <= 10 -> {
                binding.btnIncrease.isClickable = false
                binding.btnIncrease.backgroundTintList = resources.getColorStateList(android.R.color.darker_gray)

            }
            else -> {
                binding.btnIncrease.isClickable = true
                binding.btnIncrease.backgroundTintList = resources.getColorStateList(R.color.teal_700)
                binding.txtCount.text = "0"
            }
        }
    }


//    // ====================== AsyncTask ==========================
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