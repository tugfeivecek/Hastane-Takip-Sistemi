package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.EmergencyResponse
import com.tugfeivecek.hastanetakipsistemi.model.EmergencyStatu
import com.tugfeivecek.hastanetakipsistemi.model.Pharmacy
import com.tugfeivecek.hastanetakipsistemi.model.PharmacyResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EmergencyStatuViewModel : ViewModel() {

    private val emergencyStatuApiService = HospitalAPIService()
    private val emergencyStatuDisposable = CompositeDisposable()
    val emergencyStatuList = MutableLiveData<List<EmergencyStatu>>()

    fun refreshEmergencyStatu() {
        getNearMapDataFromAPI()
    }

    private fun getNearMapDataFromAPI() {

        emergencyStatuDisposable.add(
            emergencyStatuApiService.getEmergencyStatuData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EmergencyResponse>() {

                    override fun onSuccess(t: EmergencyResponse) {
                        emergencyStatuList.value = t.allEmergencyStatus
                        for (data in t.allEmergencyStatus!!) {
                            Log.e("Data:", data.hospitalName.toString())
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}