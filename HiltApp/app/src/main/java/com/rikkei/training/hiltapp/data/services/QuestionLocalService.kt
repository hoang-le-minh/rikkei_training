package com.rikkei.training.hiltapp.data.services

import com.rikkei.training.hiltapp.data.database.question.QuestionDao
import com.rikkei.training.hiltapp.data.database.question.QuestionEntity
import javax.inject.Inject

class QuestionLocalService @Inject constructor(private val questionDao: QuestionDao){


    suspend fun deleteAllQuestion(){
        questionDao.deleteAll()
    }

    suspend fun getAllQuestion(): List<QuestionEntity>{
        return questionDao.getAll()
    }

    suspend fun saveListQuestion(questions: List<QuestionEntity>){
        questionDao.insertAll(questions)
    }


}