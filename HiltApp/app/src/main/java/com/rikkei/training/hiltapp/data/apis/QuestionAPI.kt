package com.rikkei.training.hiltapp.data.apis

import com.rikkei.training.hiltapp.data.entities.QuestionList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface QuestionAPI {

    /**
     *  example: https://api.stackexchange.com/2.2/questions?page=1&site=stackoverflow&pagesize=1
     */
    @GET("/questions")
    suspend fun getListQuestions(
        @HeaderMap headers: Map<String, String> = mapOf(),
        @QueryMap parameters: Map<String, String> = mapOf()
    ): Response<QuestionList>

}