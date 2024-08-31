package com.pw.pwrickapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pw.pwrickapp.model.NetworkState
import com.pw.pwrickapp.model.Results
import com.pw.pwrickapp.repository.CharacterDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ritik on: 31/08/24
 */

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterDataRepository) : ViewModel() {

    private val _characters = MutableLiveData<NetworkState<ArrayList<Results>>?>()
    val characters: MutableLiveData<NetworkState<ArrayList<Results>>?> get() = _characters


    private val _characterDetails = MutableLiveData<NetworkState<Results>?>()
    val characterDetails: MutableLiveData<NetworkState<Results>?> get() = _characterDetails

    /**
     * Get list of Characters
     */
    fun getCharacterList() {
        _characters.value = NetworkState.Loading()

        viewModelScope.launch {
            try {
                val response = repository.getCharacterData()
                _characters.value = NetworkState.Success(
                    response.results, response.message)

            } catch (ex: Exception) {

            }
        }
    }


    /**
     * Get All Records
     */
    fun getCharacterDetails(id: Int) {
        _characterDetails.value = NetworkState.Loading()
        viewModelScope.launch {
            try {
                val response = repository.getCharacterDetailsData(id)
                // Assuming the character details are in the `results` field
                _characterDetails.value = NetworkState.Success(response)

            } catch (ex: Exception) {
                _characterDetails.value = NetworkState.Error(ex.message ?: "An error occurred")
            }
        }
    }

}


