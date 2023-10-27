package com.rikkei.training.mediaplayer2

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.rikkei.training.mediaplayer2.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("lifecycle", "Fragment A: onCreateView")
        binding = FragmentABinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.btnNavToB.setOnClickListener {
            view.findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
        }

        return view
    }

    // fragment is attached to the host activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("lifecycle", "Fragment A: onAttach")
    }
    // called after the fragment is attached, starting the fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "Fragment A: onCreate")
    }
    // activity finishes executing onCreate
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("lifecycle", "Fragment A: onActivityCreated")
    }

    // activity finishes executing onCreate
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    // fragment is displayed
    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "Fragment A: onStart")
    }

    // Fragment is visible and interactive
    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "Fragment A: onResume")
    }

    // can't interacted with, fragment is about to be replaced, deleted or activity on pause
    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "Fragment A: onPause")
    }

    // don't displayed, replaced, deleted or activity on stop
    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "Fragment A: onStop")
    }

    // view and resource created in onCreateView are removed from activity
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifecycle", "Fragment A: onDestroyView")
    }

    // destroy fragment
    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "Fragment A: onDestroy")
    }

    // detach fragment from activity
    override fun onDetach() {
        super.onDetach()
        Log.d("lifecycle", "Fragment A: onDetach")
    }
}