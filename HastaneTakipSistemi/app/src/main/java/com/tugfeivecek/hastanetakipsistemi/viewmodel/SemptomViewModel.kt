package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Semptom
import com.tugfeivecek.hastanetakipsistemi.model.SemptomResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SemptomViewModel : ViewModel() {

    private val apiService = HospitalAPIService()
    private val disposable = CompositeDisposable()
    val semptom = MutableLiveData<List<Semptom>>()

    fun refreshSemptomData() {
        getSemptomDataFromAPI()
    }

    private fun getSemptomDataFromAPI() {

        disposable.add(
            apiService.getSemptomData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SemptomResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: SemptomResponse) {

                        semptom.value = t.allSemptom
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}