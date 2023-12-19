package com.rikkei.training.hiltapp.data.services

import com.rikkei.training.hiltapp.data.apis.PostAPI
import com.rikkei.training.hiltapp.data.entities.Post
import javax.inject.Inject

class PostRemoteService @Inject constructor(private  val postAPI: PostAPI) {

    suspend fun getPosts(): List<Post>? {

        val response = postAPI.getPosts()

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }


}