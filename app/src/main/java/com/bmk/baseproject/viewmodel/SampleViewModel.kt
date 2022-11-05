package com.bmk.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bmk.baseproject.util.ResponseState
import com.bmk.domain.Repository
import com.bmk.domain.Response
import com.bmk.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val mutableMainState = MutableLiveData<ResponseState<Response>>()
    fun getData(): MutableLiveData<ResponseState<Response>> {

        viewModelScope.launch {
            mutableMainState.value = ResponseState.Loading
            when (val result = withContext(Dispatchers.IO) { repository.getData() }) {
                is Result.Failure -> {
                    mutableMainState.value =
                        ResponseState.Error(result.exception)
                }
                is Result.Success -> {
                    mutableMainState.value =
                        ResponseState.Success(result.data)
                }
            }
        }
        return mutableMainState
    }


    override fun onCleared() {
        TODO("Not yet implemented")
    }
}