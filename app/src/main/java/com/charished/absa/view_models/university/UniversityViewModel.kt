package com.charished.absa.view_models.university

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.charished.absa.models.repository.UniversityRepo

class UniversityViewModel @ViewModelInject constructor(
    private val repo: UniversityRepo
) : ViewModel(){
    val universities = repo.getUniversities()
}