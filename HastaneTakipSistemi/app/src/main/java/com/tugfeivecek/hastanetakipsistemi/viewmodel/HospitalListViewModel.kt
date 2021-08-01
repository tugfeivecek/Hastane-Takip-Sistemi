package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Hospital
import com.tugfeivecek.hastanetakipsistemi.model.HospitalList
import com.tugfeivecek.hastanetakipsistemi.model.HospitalListResponse
import com.tugfeivecek.hastanetakipsistemi.model.HospitalResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HospitalListViewModel : ViewModel() {

    private val hospitalListApiService = HospitalAPIService()
    private val hospitalListDisposable = CompositeDisposable()
    val hospitalListData = MutableLiveData<List<HospitalList>>()
    val allHospital = MutableLiveData<List<Hospital>>()

    fun refreshHospitalListData(ilceId: String) {
        getHospitalListDataFromAPI(ilceId)
    }

    fun refreshAllHospitalListData() {
        getAllHospital()
    }

    private fun getHospitalListDataFromAPI(ilceId: String) {

        hospitalListDisposable.add(
            hospitalListApiService.getHospitalListData(ilceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HospitalListResponse>() {
                    override fun onSuccess(t: HospitalListResponse) {
                        hospitalListData.value = t.allHospitalList
                        Log.e("Data", "Başarılı")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.e("Data", "Başarısız")
                    }
                })
        )
    }

    private fun getAllHospital() {

        hospitalListDisposable.add(
            hospitalListApiService.getHospitalData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HospitalResponse>() {
                    override fun onSuccess(t: HospitalResponse) {
                        allHospital.value = t.allHospital

                        Log.e("Data", "Başarılı")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.e("Data", "Başarısız")
                    }
                })
        )
    }
}