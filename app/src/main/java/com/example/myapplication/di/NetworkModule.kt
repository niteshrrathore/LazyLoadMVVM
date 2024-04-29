package com.example.myapplication.di

import com.example.myapplication.core.utils.Constants
import com.example.myapplication.data.remote.network.ApiService
import com.example.myapplication.data.repoository.GetPostRepositoryImpl
import com.example.myapplication.domain.repository.GetPostRepository
import com.example.myapplication.domain.useCases.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesGetPostRepository(apiService: ApiService) : GetPostRepository {
        return GetPostRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideGetPostUseCase(getPostRepository: GetPostRepository): GetPostUseCase {
        return GetPostUseCase(getPostRepository)
    }
}