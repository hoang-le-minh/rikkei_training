package com.rikkei.training.hiltapp.data.repositories

import com.rikkei.training.hiltapp.data.entities.Post
import com.rikkei.training.hiltapp.data.services.PostRemoteService
import com.rikkei.training.hiltapp.di.IODispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postRemoteService: PostRemoteService,
    @IODispatchers private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getPost(): List<Post>? = withContext(dispatcher) {
        postRemoteService.getPosts()
    }

}