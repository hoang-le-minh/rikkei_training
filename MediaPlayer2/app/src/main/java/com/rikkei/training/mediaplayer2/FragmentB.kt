package com.rikkei.training.mediaplayer2

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rikkei.training.mediaplayer2.databinding.FragmentBBinding


class FragmentB : Fragment() {
    private lateinit var binding: FragmentBBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mainActivity: MainActivity

    private val args by navArgs<FragmentBArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        mainActivity = activity as MainActivity

        KhoiTaoMediaPlayer()
        mediaPlayer.start()
        binding.btnBackToA.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("pauseA", args.pauseA)
            view.findNavController().popBackStack()
            Log.d("lifecycle", "Fragment B -> Fragment A")
        }

        return view
    }

    private fun KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.mot_cu_lua)
    }

    // fragment is attached to the host activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("lifecycle", "Fragment B: onAttach")
    }
    // called after the fragment is attached, starting the fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "Fragment B: onCreate")
    }
    // activity finishes executing onCreate
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("lifecycle", "Fragment B: onActivityCreated")
    }

    // activity finishes executing onCreate
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    // fragment is displayed
    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "Fragment B: onStart")
    }

    // Fragment is visible and interactive
    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "Fragment B: onResume")
    }

    // can't interacted with, fragment is about to be replaced, deleted or activity on pause
    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "Fragment B: onPause")
    }

    // don't displayed, replaced, deleted or activity on stop
    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "Fragment B: onStop")

        mediaPlayer.stop()
    }

    // view and resource created in onCreateView are removed from activity
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifecycle", "Fragment B: onDestroyView")
    }

    // destroy fragment
    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "Fragment B: onDestroy")
    }

    // detach fragment from activity
    override fun onDetach() {
        super.onDetach()
        Log.d("lifecycle", "Fragment B: onDetach")
    }

}