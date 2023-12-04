package com.rikkei.training.sharepreference

import androidx.databinding.ObservableField

class Activity2ViewModel(val content: ObservableField<String>) {

    fun changeValueContent(){
        content.set("New Content")
    }
}