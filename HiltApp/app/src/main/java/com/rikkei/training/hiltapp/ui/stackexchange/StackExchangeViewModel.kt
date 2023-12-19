package com.rikkei.training.hiltapp.ui.stackexchange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rikkei.training.hiltapp.base.BaseViewModel
import com.rikkei.training.hiltapp.data.entities.Question
import com.rikkei.training.hiltapp.data.repositories.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StackExchangeViewModel @Inject constructor(private val questionRepository: QuestionRepository): BaseViewModel() {

    var listQuestions = MutableLiveData<List<Question>>()
        private set

    fun fetchData() {
        parentJob = viewModelScope.launch(exceptionHandler) {
            isLoading.postValue(true)
            val questions = questionRepository.getListQuestion()
            if (questions.isNotEmpty()){
                listQuestions.postValue(questions)
            }
        }

        registerEventParentJobFinish()
    }


    fun refresh() {
        parentJob = viewModelScope.launch(exceptionHandler) {
            isLoading.postValue(true)
            val questions = questionRepository.getNewAndSave()
            if (questions.isNotEmpty()){
                listQuestions.postValue(questions)
            }
        }
        registerEventParentJobFinish()
    }
}