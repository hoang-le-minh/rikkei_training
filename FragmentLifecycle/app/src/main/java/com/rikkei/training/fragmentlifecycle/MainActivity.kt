package com.rikkei.training.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.rikkei.training.fragmentlifecycle.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frm_layout2, FragmentC())
        fragmentTransaction.commit()

        binding.btnFragmentA.setOnClickListener {
            replaceFragment(FragmentA())
        }

        binding.btnFragmentB.setOnClickListener {
            replaceFragment(FragmentB())
        }


        binding.btnSendToA.setOnClickListener {
            sendDataToFragment(FragmentA())
        }
        binding.btnSendToB.setOnClickListener {
            sendDataToFragment(FragmentB())
        }

    }

    private fun sendDataToFragment(fragment: Fragment) {
        var email = binding.edtEmail.text.toString()
        if(email == ""){
            email = "someone@gmail.com"
        }

        val bundle = Bundle()
        val key_email = when(fragment){
            is FragmentA -> "emailA"
            is FragmentB -> "emailB"
            else -> "email"
        }
        bundle.putString(key_email, email)
        fragment.arguments = bundle

        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frm_layout, fragment)

        fragmentTransaction.commit()
    }
//    fun getEmail(): String{
//        var email = binding.edtEmail.text.toString()
//        if(email == ""){
//            email = "someone@gmail.com"
//        }
//        return email
//    }
    fun getEdtEmail(): EditText{
        return binding.edtEmail
    }


}