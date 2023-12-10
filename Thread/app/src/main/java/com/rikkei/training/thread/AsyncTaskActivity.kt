package com.rikkei.training.thread

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.rikkei.training.thread.databinding.ActivityAsyncTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AsyncTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsyncTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncrease.setOnClickListener {
            doIncreaseNumber()
        }
    }

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

    fun <P, R> CoroutineScope.executeAsyncTask(
        onPreExecute: () -> Unit,
        doInBackground: suspend (suspend (P) -> Unit) -> R,
        onPostExecute: (R) -> Unit,
        onProgressUpdate: (P) -> Unit
    ) = launch {
        onPreExecute()

        val result = withContext(Dispatchers.IO) {
            doInBackground {
                withContext(Dispatchers.Main) { onProgressUpdate(it) }
            }
        }
        onPostExecute(result)
    }
}