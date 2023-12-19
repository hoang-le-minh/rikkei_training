package com.rikkei.training.hiltapp.data.services

import com.rikkei.training.hiltapp.data.apis.QuestionAPI
import com.rikkei.training.hiltapp.data.entities.QuestionList
import javax.inject.Inject

class QuestionRemoteService @Inject constructor(private val questionAPI: QuestionAPI) {

    suspend fun getListQuestion(currentPage: Int, pageSize: Int): QuestionList?{

        val parameters = mutableMapOf<String,String>()
        parameters["site"] = "stackoverflow"
        parameters["pagesize"] = "$pageSize"
        parameters["page"] = "$currentPage"
        val response =  questionAPI.getListQuestions(parameters = parameters)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }

    }

}