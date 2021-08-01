package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Hospital
import com.tugfeivecek.hastanetakipsistemi.model.HospitalResponse
import com.tugfeivecek.hastanetakipsistemi.model.Pharmacy
import com.tugfeivecek.hastanetakipsistemi.model.PharmacyResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NearPharmacyViewModel : ViewModel() {

    private val nearMapApiService = HospitalAPIService()
    private val nearMapDisposable = CompositeDisposable()
    val locationMapPharmacy = MutableLiveData<List<Pharmacy>>()

    fun refreshNearMapPharmacyData() {
        getNearMapDataFromAPI()
    }

    private fun getNearMapDataFromAPI() {

        nearMapDisposable.add(
            nearMapApiService.getPharmacyLocationData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PharmacyResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: PharmacyResponse) {
                        locationMapPharmacy.value = t.allPharmacy

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}