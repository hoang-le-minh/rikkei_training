package com.rikkei.training.mvvm

import android.text.TextUtils
import android.util.Patterns

data class User(
    val email: String,
    val password: String
) {
    fun isValidEmail(): Boolean{
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPassword(): Boolean{
        return !TextUtils.isEmpty(password) && password.length >= 6
    }
}