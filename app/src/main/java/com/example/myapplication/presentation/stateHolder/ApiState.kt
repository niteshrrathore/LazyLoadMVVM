package com.example.myapplication.presentation.stateHolder

import com.example.myapplication.domain.model.PostsList

sealed class ApiState {
    class Success(val data: List<PostsList>) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading: ApiState()
    object Empty: ApiState()
}