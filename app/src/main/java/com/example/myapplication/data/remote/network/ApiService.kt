package com.example.myapplication.data.remote.network

import com.example.myapplication.domain.model.Posts
import com.example.myapplication.domain.model.PostsList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts() : Response<List<Posts>>

}