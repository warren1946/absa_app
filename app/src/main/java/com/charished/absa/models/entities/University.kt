package com.charished.absa.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities")
data class University(
    @PrimaryKey
    val id: Int,
    val web_page: String,
    val country: String,
    val domain: String,
    val name: String
)