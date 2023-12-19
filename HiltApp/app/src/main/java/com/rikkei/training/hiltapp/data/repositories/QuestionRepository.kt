package com.rikkei.training.hiltapp.data.repositories

import com.rikkei.training.hiltapp.data.entities.Question
import com.rikkei.training.hiltapp.data.entities.toListQuestion
import com.rikkei.training.hiltapp.data.entities.toListQuestionEntity
import com.rikkei.training.hiltapp.data.services.QuestionLocalService
import com.rikkei.training.hiltapp.data.services.QuestionRemoteService
import com.rikkei.training.hiltapp.di.IODispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionRemoteService: QuestionRemoteService,
                                     private val localService: QuestionLocalService,
                                     @IODispatchers private val dispatcher: CoroutineDispatcher
) {

    suspend fun getListQuestion(): List<Question> = withContext(dispatcher){
        val saveQuestion = localService.getAllQuestion()
        if(saveQuestion.isNotEmpty()){
            saveQuestion.toListQuestion()
        } else {
            getNewAndSave()
        }
    }

    suspend fun getNewAndSave(): List<Question> {
        val questionList = questionRemoteService.getListQuestion(1, 1)
        val newListQuestion = questionList?.items ?: emptyList()

        if(newListQuestion.isNotEmpty()){
            localService.deleteAllQuestion()
            localService.saveListQuestion(newListQuestion.toListQuestionEntity())
        }

        return newListQuestion
    }

}