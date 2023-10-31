package com.rikkei.training.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.rikkei.training.fragmentlifecycle.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding

    private lateinit var mainActivity: MainActivity

    // initialize view component and add it to the activity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifecycle", "Fragment A: onCreateView")

        // Inflate the layout for this fragment
        binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding.root

        mainActivity = activity as MainActivity

        binding.edtEmailA.setText(arguments?.getString("emailA"))

        binding.btnUpdateA.setOnClickListener {
            sendDataToActivity()
        }

        binding.btnNavToB.setOnClickListener {
            navToFragmentB()
        }

        binding.btnSendToC.setOnClickListener {
            sendDataToC()
        }

        return view
    }

    private fun sendDataToC() {
        val email = binding.edtEmailA.text.toString()
        val fragmentC = mainActivity.supportFragmentManager.findFragmentById(R.id.frm_layout2) as? FragmentC
        fragmentC?.receiverData(email)
    }

    private fun sendDataToActivity() {
        val email = binding.edtEmailA.text.toString()
        mainActivity.getEdtEmail().setText(email)
    }

    private fun navToFragmentB(){
        Log.d("lifecycle", "Fragment A -> Fragment B")
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frm_layout, FragmentB())
        fragmentTransaction.commit()
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