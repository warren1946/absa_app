package com.charished.absa.models.repository

import com.charished.absa.models.local.UniversityDao
import com.charished.absa.models.remote.UniversityRemoteDataSource
import com.charished.absa.utils.performGetOperation
import javax.inject.Inject

class UniversityRepo @Inject constructor(
    private val remoteDataSource: UniversityRemoteDataSource,
    private val localDataSource: UniversityDao
) {

    fun getUniversities() = performGetOperation(
        databaseQuery = { localDataSource.getAllUniversities() },
        networkCall = { remoteDataSource.getUniversities() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getUniversityByCountry(country: String) = performGetOperation(
        databaseQuery = { localDataSource.getUniversityByCountry(country) },
        networkCall = { remoteDataSource.getUniversityByCountry(country) },
        saveCallResult = { localDataSource.updateAll(it) }
    )
}