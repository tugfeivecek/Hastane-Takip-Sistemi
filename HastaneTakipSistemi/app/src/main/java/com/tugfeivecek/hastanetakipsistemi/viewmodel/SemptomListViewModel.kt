package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.SemptomDetail
import com.tugfeivecek.hastanetakipsistemi.model.SemptomDetailResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SemptomListViewModel : ViewModel() {

    private val semptomListApiService = HospitalAPIService()
    private val disposableSemptomList = CompositeDisposable()
    val semptomsList = MutableLiveData<List<SemptomDetail>>()

    fun refreshSemptomListData(type_id: String) {
        getSemptomListDataFromAPI(type_id)
    }

    private fun getSemptomListDataFromAPI(type_id: String) {

        disposableSemptomList.add(
            semptomListApiService.getSemptomDetailData(type_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SemptomDetailResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: SemptomDetailResponse) {

                        semptomsList.value = t.allSemptomDetail

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}