package com.charished.absa.models.remote

import com.charished.absa.models.entities.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UniversityService {

    @GET("search?name=middle")
    suspend fun getUniversities() : Response<List<University>>

    @GET("search")
    suspend fun getUniversityByCountry(@QueryMap options: Map<String, String>): Response<List<University>>
}