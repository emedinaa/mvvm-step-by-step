package com.emedinaa.kotlinmvvm.model

import androidx.lifecycle.LiveData

interface MuseumDataSource {
    suspend fun retrieveMuseums():List<Museum>
}