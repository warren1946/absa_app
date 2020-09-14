package com.charished.absa.view_models.country

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.charished.absa.models.entities.University
import com.charished.absa.models.repository.UniversityRepo
import com.charished.absa.utils.Resource

class CountryUniversityViewModel @ViewModelInject constructor(
    private val repository: UniversityRepo
) : ViewModel(){

    private val _country = MutableLiveData<String>()

    private val _universities = _country.switchMap { country ->
        repository.getUniversityByCountry(country)
    }

    val universities: LiveData<Resource<List<University>>> = _universities

    fun start(country: String) {
        _country.value = country
    }
}