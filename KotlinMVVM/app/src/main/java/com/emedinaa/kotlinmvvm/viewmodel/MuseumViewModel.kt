package com.emedinaa.kotlinmvvm.viewmodel

import androidx.lifecycle.*
import com.emedinaa.kotlinmvvm.model.MuseumDataSource
import kotlinx.coroutines.Dispatchers

class MuseumViewModel(private val repository: MuseumDataSource):ViewModel() {

    fun  getMuseums() = liveData(Dispatchers.IO) {
        val result = repository.retrieveMuseums()
        emit(result)
    }
}