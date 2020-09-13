package com.charished.absa.models.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.charished.absa.models.entities.University

@Database(entities = [University::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao

    companion object {
        @Volatile private var instance: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, LocalDatabase::class.java, "universities")
                .fallbackToDestructiveMigration()
                .build()
    }
}