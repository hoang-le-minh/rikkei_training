package com.rikkei.training.matchimagegame

import android.app.AlertDialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rikkei.training.matchimagegame.databinding.FragmentPlayBinding
import java.text.SimpleDateFormat

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    private var remainingTime: Long = 60000
    private var buttonCount = 20

    private var listImage = mutableListOf(
        R.drawable.pokemon1, R.drawable.pokemon2, R.drawable.pokemon3, R.drawable.pokemon4,
        R.drawable.pokemon5, R.drawable.pokemon6, R.drawable.pokemon7, R.drawable.pokemon8,
        R.drawable.pokemon9, R.drawable.pokemon10, R.drawable.pokemon1, R.drawable.pokemon2,
        R.drawable.pokemon3, R.drawable.pokemon4, R.drawable.pokemon5, R.drawable.pokemon6,
        R.drawable.pokemon7, R.drawable.pokemon8, R.drawable.pokemon9, R.drawable.pokemon10
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("listScore", getListScore().toString())
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val displayMetrics = resources.displayMetrics
        val width = displayMetrics.widthPixels

        binding.frmImage.layoutParams.height = width*5/4

        listImage.shuffle()

        var score = 0
        var clickCount = 0
        val maxClickButton = 2
        val arrClick = mutableListOf<ImageButton>()

        countDownTimer.start()

        for (i in 1..20){
            val btnId = resources.getIdentifier("img$i", "id", "com.rikkei.training.matchimagegame")
            val btn = view.findViewById<ImageButton>(btnId)
            btn.setOnClickListener {
                if(clickCount < maxClickButton){
                    btn.setImageResource(listImage[i-1])
                    arrClick.add(clickCount, btn)
                    clickCount++
                }
                if(clickCount == maxClickButton){
                    val img1Drawable = arrClick[0].drawable as BitmapDrawable
                    val img1Bitmap = img1Drawable.bitmap
                    val img2Drawable = arrClick[1].drawable as BitmapDrawable
                    val img2Bitmap = img2Drawable.bitmap
//                    val hand = Handler()
//                    val run = Runnable {  }
//                    hand.removeCallbacks(run)
                    Handler(Looper.getMainLooper()).postDelayed({

                        if(img1Bitmap == img2Bitmap){
                            arrClick[0].visibility = View.INVISIBLE
                            arrClick[1].visibility = View.INVISIBLE
                            buttonCount -= 2
                            score += 10
                            binding.txtScore.text = score.toString()
                            if(buttonCount == 0){
                                openDialogGameOver(score)
                                countDownTimer.cancel()
                            }
                        } else {
                            arrClick[0].setImageResource(R.drawable.question_mark)
                            arrClick[1].setImageResource(R.drawable.question_mark)
                            if (score > 0) {
                                score -= 1
                            } else score = 0
                            binding.txtScore.text = score.toString()

                        }
                        clickCount = 0
                    }, 750)


                }


            }

        }

        return view
    }

    private fun openDialogGameOver(currentScore: Int){

        val score = Score(currentScore, customTime())
        saveScore(score)

        val openDialog = AlertDialog.Builder(requireContext())
        openDialog.setTitle("Game Over...")
        openDialog.setMessage("Score: $currentScore")
        openDialog.setCancelable(false)
        openDialog.setPositiveButton("Replay"){
            dialog, _ ->
            restartGame()
            dialog.dismiss()
        }
        openDialog.setNegativeButton("Home"){
            dialog, _ ->
            findNavController().popBackStack()
            dialog.dismiss()
        }
        openDialog.create()
        openDialog.show()
    }

    private fun restartGame(){
        val mainActivity = activity as MainActivity
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, PlayFragment())
        fragmentTransaction.commit()
    }

    private val countDownTimer = object: CountDownTimer(remainingTime, 1000){
        override fun onTick(p0: Long) {
            remainingTime = p0
            if(remainingTime <= 15000){
                binding.txtTimer.setTextColor(Color.parseColor("#750128"))
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.txtTimer.setTextColor(Color.parseColor("#E91E63"))
                }, 500)
            }
            binding.txtTimer.text = "${remainingTime/1000}s"
        }

        override fun onFinish() {
            if(buttonCount > 0){
                openDialogGameOver(Integer.parseInt(binding.txtScore.text.toString()))
            }
        }

    }

    private fun getEncryptedSharedPreferences(): SharedPreferences {
        val masterKeyAlias = MasterKey.Builder(requireContext()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        return EncryptedSharedPreferences.create(
            requireContext(),
            "secured_data_history_prefs",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun saveScore(score: Score){
        val list = getListScore()
        val gson = Gson()
        list.add(0, score)
        if(list.size > 5){
            for (i in list.size downTo  6){
                list.removeAt(i-1)
            }
        }
        val json = gson.toJson(list)//converting list to Json
        getEncryptedSharedPreferences().edit()
            .putString("LIST",json)
            .apply()

    }

    private fun getListScore(): MutableList<Score>{
        val gson = Gson()
        val json = getEncryptedSharedPreferences().getString("LIST",null)
        val type = object : TypeToken<MutableList<Score>>(){}.type //converting the json to list
        return gson.fromJson(json,type)
    }

    private fun customTime(): String{
        val customTime = SimpleDateFormat("hh:mm:ss dd/MM/yy")
        val currentTime = System.currentTimeMillis()
        return customTime.format(currentTime)
    }

}