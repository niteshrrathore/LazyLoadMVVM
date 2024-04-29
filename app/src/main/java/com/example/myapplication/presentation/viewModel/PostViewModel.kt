package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Posts
import com.example.myapplication.domain.useCases.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val getProductUseCase: GetPostUseCase): ViewModel() {

    private val _postList = MutableLiveData<List<Posts>?>()
    val postList: LiveData<List<Posts>?> = _postList
    var errorMessage = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()

    init {
        getAllPosts()
    }

    fun getAllPosts() = viewModelScope.launch {
        getProductUseCase().onEach {
            when(it){
                is Resource.Loading -> {
                    loading.postValue(true)
                }
                is Resource.Success -> {
                    _postList.postValue(it.data)
                    loading.postValue(false)
                }
                is Resource.Error -> {
                    loading.postValue(false)
                    errorMessage.value = it.message.toString()
                }
            }
        }.launchIn(viewModelScope)
    }

}