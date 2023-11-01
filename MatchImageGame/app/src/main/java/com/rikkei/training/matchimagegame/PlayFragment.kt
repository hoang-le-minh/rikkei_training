package com.rikkei.training.matchimagegame

import android.app.AlertDialog
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.rikkei.training.matchimagegame.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    private var remainingTime: Long = 60000

    private var listImage = mutableListOf<Int>(
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
        var buttonCount = 20
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

                    Handler(Looper.getMainLooper()).postDelayed({

                        if(img1Bitmap == img2Bitmap){
                            arrClick[0].visibility = View.INVISIBLE
                            arrClick[1].visibility = View.INVISIBLE
                            buttonCount -= 2
                            score += 10
                            binding.txtScore.text = score.toString()
                            if(buttonCount == 0){
                                openDialogGameOver(score)
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
        val score = binding.txtScore.text.toString()
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
            binding.txtTimer.text = "${remainingTime/1000}s"
        }

        override fun onFinish() {
            openDialogGameOver(Integer.parseInt(binding.txtScore.text.toString()))
        }

    }

}