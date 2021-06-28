package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.District
import com.tugfeivecek.hastanetakipsistemi.model.DistrictResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DistrictViewModel : ViewModel() {

    private val districtApiService = HospitalAPIService()
    private val districtDisposable = CompositeDisposable()
    val districtData = MutableLiveData<List<District>>()

    fun refreshDistrictData(ilId: String) {
        getDistrictDataFromAPI(ilId)
    }

    private fun getDistrictDataFromAPI(ilId: String) {

        districtDisposable.add(
            districtApiService.getDistrictData(ilId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DistrictResponse>() {
                    override fun onSuccess(t: DistrictResponse) {
                        districtData.value = t.allDistrict
                        Log.e("t.successDistrict", t.successDistrict.toString())
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.e("Data", "Başarısız")
                    }
                })
        )
    }

}