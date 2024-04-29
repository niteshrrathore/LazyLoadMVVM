package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Posts
import com.example.myapplication.domain.model.PostsList
import retrofit2.Response

interface GetPostRepository {
    suspend fun getPosts() : Response<List<Posts>>

}