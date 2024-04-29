package com.example.myapplication.domain.useCases

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Posts
import com.example.myapplication.domain.model.PostsList
import com.example.myapplication.domain.repository.GetPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val getProductRepository: GetPostRepository) {
    operator fun invoke(): Flow<Resource<List<Posts>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = getProductRepository.getPosts().body()))
        } catch (e : Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}