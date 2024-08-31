package com.pw.pwjetpackapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pw.pwjetpackapp.model.Results
import com.pw.pwjetpackapp.repository.CharacterRepository
import kotlinx.coroutines.launch


/**
 * Created by Ritik on: 31/08/24
 */

class CharacterListViewModel(private val repository: CharacterRepository) : ViewModel() {
    private val _characters = MutableLiveData<List<Results>>()
    val characters: LiveData<List<Results>> = _characters

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getCharacters()
            if (response.isSuccessful) {
                _characters.value = response.body()?.results
            } else {

            }
            _isLoading.value = false
        }
    }
}