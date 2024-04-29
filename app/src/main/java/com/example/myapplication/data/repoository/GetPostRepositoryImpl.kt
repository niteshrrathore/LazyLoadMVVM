package com.example.myapplication.data.repoository

import com.example.myapplication.data.remote.network.ApiService
import com.example.myapplication.domain.model.Posts
import com.example.myapplication.domain.model.PostsList
import com.example.myapplication.domain.repository.GetPostRepository
import retrofit2.Response
import javax.inject.Inject

class GetPostRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetPostRepository {

    override suspend fun getPosts(): Response<List<Posts>> {
        return apiService.getPosts()
    }
}