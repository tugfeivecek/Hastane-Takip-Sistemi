package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Hospital
import com.tugfeivecek.hastanetakipsistemi.model.HospitalResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LocationViewModel : ViewModel() {

    private val locationApiService = HospitalAPIService()
    private val locationDisposable = CompositeDisposable()
    val locationData = MutableLiveData<List<Hospital>>()

    fun refreshLocationData() {
        getLocationDataFromAPI()
    }

    private fun getLocationDataFromAPI() {

        locationDisposable.add(
            locationApiService.getHospitalData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HospitalResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: HospitalResponse) {
                        locationData.value = t.allHospital
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}