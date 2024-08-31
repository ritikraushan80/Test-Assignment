package com.pw.pwjetpackapp.viewmodel


/**
 * Created by Ritik on: 29/08/24
 */

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pw.pwjetpackapp.model.Results
import com.pw.pwjetpackapp.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val repository: CharacterRepository) : ViewModel() {
    private val _characterDetail = MutableLiveData<Results>()
    val characterDetail: LiveData<Results> = _characterDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadCharacterDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getCharacterDetail(id)
            if (response.isSuccessful) {
                _characterDetail.value = response.body()
            } else {
                 Log.e(TAG, response.errorBody().toString())
            }
            _isLoading.value = false
        }
    }
}