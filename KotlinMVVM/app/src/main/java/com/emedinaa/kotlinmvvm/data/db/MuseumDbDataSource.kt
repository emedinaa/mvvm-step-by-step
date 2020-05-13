package com.emedinaa.kotlinmvvm.data.db

import android.content.Context
import androidx.lifecycle.LiveData

class MuseumDbDataSource(context:Context):DbDataSource {
    private lateinit var museumDao:MuseumDao
    init {
        val db = MuseumDataBase.getInstance(context)
        db?.let {
            museumDao = it.museumDao()
        }
    }
    override fun museums(): LiveData<List<MuseumDTO>> {
        return museumDao.museums()
    }

    override suspend fun addMuseums(museums: List<MuseumDTO>) {
        return museumDao.add(museums)
    }

    override suspend fun deleteMuseums() {
        museumDao.deleteAll()
    }
}