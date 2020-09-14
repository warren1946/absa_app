package com.charished.absa.models.remote

import javax.inject.Inject

class UniversityRemoteDataSource @Inject constructor(
    private val characterService: UniversityService
): BaseDataSource() {

    suspend fun getUniversities() = getResult { characterService.getUniversities() }
    suspend fun getUniversityByCountry(country: String) = getResult { characterService.getUniversityByCountry(
        createMap(country)
    ) }

    fun createMap(country: String): Map<String, String>{
        val data: MutableMap<String, String> = HashMap()
        data["name"] = "middle"
        data["country"] = country
        return data
    }
}