package com.charished.absa.models.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charished.absa.models.entities.University

@Dao
interface UniversityDao {

    @Query("SELECT * FROM universities")
    fun getAllUniversities() : LiveData<List<University>>

    @Query("SELECT * FROM universities WHERE id = :id")
    fun getUniversity(id: Int): LiveData<University>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<University>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(university: University)
}