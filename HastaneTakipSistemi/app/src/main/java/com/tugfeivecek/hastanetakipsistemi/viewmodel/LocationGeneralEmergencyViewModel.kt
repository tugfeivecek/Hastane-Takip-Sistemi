package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Hospital
import com.tugfeivecek.hastanetakipsistemi.model.HospitalResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LocationGeneralEmergencyViewModel : ViewModel() {

    private val generalEmergencyApiService = HospitalAPIService()
    private val generalEmergencyDisposable = CompositeDisposable()
    val generalEmergencyData = MutableLiveData<List<Hospital>>()

    fun refreshgeneralEmergencyData() {
        getgeneralEmergencyDataFromAPI()
    }

    private fun getgeneralEmergencyDataFromAPI() {

        generalEmergencyDisposable.add(
            generalEmergencyApiService.getHospitalData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HospitalResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: HospitalResponse) {
                        generalEmergencyData.value = t.allHospital
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}