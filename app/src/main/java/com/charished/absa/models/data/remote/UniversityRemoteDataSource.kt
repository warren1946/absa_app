package com.charished.absa.models.data.remote

import javax.inject.Inject

class UniversityRemoteDataSource @Inject constructor( private val characterService: UniversityService
): BaseDataSource() {

}