package com.charished.absa.dependencies

import android.content.Context
import com.charished.absa.models.local.AppDatabase
import com.charished.absa.models.local.UniversityDao
import com.charished.absa.models.remote.UniversityRemoteDataSource
import com.charished.absa.models.remote.UniversityService
import com.charished.absa.models.repository.UniversityRepo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://universities.hipolabs.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUniversityService(retrofit: Retrofit): UniversityService = retrofit.create(UniversityService::class.java)

    @Singleton
    @Provides
    fun provideUniversityRemoteDataSource(characterService: UniversityService) = UniversityRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUniversityDao(db: AppDatabase) = db.universityDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UniversityRemoteDataSource,
                          localDataSource: UniversityDao) =
        UniversityRepo(remoteDataSource, localDataSource)
}