package com.rikkei.training.matchimagegame

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.rikkei.training.matchimagegame.databinding.FragmentPlayBinding
import java.util.Collections

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

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

        for (i in 1..20){
            val btnId = resources.getIdentifier("img$i", "id", "com.rikkei.training.matchimagegame")
            val btn = view.findViewById<ImageButton>(btnId)
            btn.setOnClickListener {
                btn.setImageResource(listImage[i-1])
                openDialogGameOver()
            }

        }

        return view
    }

    private fun openDialogGameOver(){
        val score = binding.txtScore.text.toString()
        val openDialog = AlertDialog.Builder(requireContext())
        openDialog.setTitle("Game Over...")
        openDialog.setMessage("Score: $score")
        openDialog.setPositiveButton("Replay"){
            dialog, _ ->
            dialog.dismiss()
        }
        openDialog.setNegativeButton("Home"){
            dialog, _ ->
            dialog.dismiss()
        }
        openDialog.create()
        openDialog.show()
    }

}