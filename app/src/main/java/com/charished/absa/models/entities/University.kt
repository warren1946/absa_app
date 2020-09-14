package com.charished.absa.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.charished.absa.utils.ListTypeConverter
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

@Entity(tableName = "universities")
data class University(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val alpha_two_code: String,
    val country: String,
    @TypeConverters(ListTypeConverter::class)
    val web_pages: ArrayList<String>,
    @TypeConverters(ListTypeConverter::class)
    val domains: ArrayList<String>
)