package com.emedinaa.kotlinmvvm.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emedinaa.kotlinmvvm.data.ApiClient

class MuseumRepository:MuseumDataSource {

    override suspend fun retrieveMuseums(): List<Museum>{
        var resultLiveData = emptyList<Museum>()
        try {
            val response = ApiClient.servicesApiInterface.museums()
            if(response.isSuccessful){
                resultLiveData= response.body()?.data?: emptyList()
            }
        }catch (e:Exception){
            Log.v("CONSOLE","e $e")
        }
        return resultLiveData
    }

}