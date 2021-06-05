package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.City
import com.tugfeivecek.hastanetakipsistemi.model.CityResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CityViewModel : ViewModel() {

    private val cityApiService = HospitalAPIService()
    private val cityDisposable = CompositeDisposable()
    val cityData = MutableLiveData<List<City>>()

    fun refreshCityData() {
        getCityDataFromAPI()
    }

    private fun getCityDataFromAPI() {

        cityDisposable.add(
            cityApiService.getCityData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CityResponse>() {
                    override fun onSuccess(t: CityResponse) {
                        cityData.value = t.allCity
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}