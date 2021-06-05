package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.EmergecnyFull
import com.tugfeivecek.hastanetakipsistemi.model.EmergecnyFullResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LocationGeneralEmergencyViewModel : ViewModel() {

    private val generalEmergencyApiService = HospitalAPIService()
    private val generalEmergencyDisposable = CompositeDisposable()
    val generalEmergencyData = MutableLiveData<List<EmergecnyFull>>()

    fun refreshgeneralEmergencyData(hastaneId: String) {
        getgeneralEmergencyDataFromAPI(hastaneId)
    }

    private fun getgeneralEmergencyDataFromAPI(hastaneId: String) {

        generalEmergencyDisposable.add(
            generalEmergencyApiService.getLocationEmergencyFullData(hastaneId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EmergecnyFullResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: EmergecnyFullResponse) {
                        generalEmergencyData.value = t.allEmergency
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}