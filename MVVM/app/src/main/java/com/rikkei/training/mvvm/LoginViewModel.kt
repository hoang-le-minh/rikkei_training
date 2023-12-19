package com.rikkei.training.mvvm

import android.os.Handler
import android.os.Looper
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginViewModel: BaseObservable() {

    private var email: String = ""
    @Bindable
    fun getEmail(): String{
        return email
    }
    fun setEmail(email: String) {
        this.email = email
        notifyPropertyChanged(BR.email)
    }

    private var password: String = ""
    @Bindable
    fun getPassword(): String {
        return password
    }
    fun setPassword(password: String) {
        this.password = password
        notifyPropertyChanged(BR.password)
    }

    var messageLogin = ObservableField<String>()
    var isShowMsg = ObservableField(false)
    var isSuccess = ObservableField<Boolean>()

    fun onclickLogin(){
        val user = User(email, password)
        isShowMsg.set(true)
        if(user.isValidEmail() && user.isValidPassword()){
            messageLogin.set("Login Success")
            isSuccess.set(true)
        } else {
            messageLogin.set("Email or password invalid")
            isSuccess.set(false)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            isShowMsg.set(false)
        }, 2000)
    }

}