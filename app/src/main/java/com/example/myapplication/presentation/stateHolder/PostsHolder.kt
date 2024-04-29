package com.example.myapplication.presentation.stateHolder

import com.example.myapplication.domain.model.Posts
import com.example.myapplication.domain.model.PostsList

data class PostsHolder(
    val isLoading:Boolean = false,
    val data : List<Posts>? = null,
    val error : String = ""
)
