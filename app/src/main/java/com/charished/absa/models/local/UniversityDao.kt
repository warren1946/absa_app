package com.charished.absa.models.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.charished.absa.models.entities.University

@Dao
interface UniversityDao {

    @Query("SELECT * FROM universities")
    fun getAllUniversities() : LiveData<List<University>>

    @Query("SELECT * FROM universities WHERE country = :country")
    fun getUniversityByCountry(country: String): LiveData<List<University>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<University>)

    @Update
    suspend fun updateAll(universities: List<University>)
}