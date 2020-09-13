package com.charished.absa.models.data.remote

import com.charished.absa.models.entities.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UniversityService {

    @GET("search?name=middle")
    suspend fun getUniversities() : Response<List<University>>

    @GET("search?name=middle&country={country}")
    suspend fun getUniversityByCountry(@Path("country") country: String): Response<Character>
}