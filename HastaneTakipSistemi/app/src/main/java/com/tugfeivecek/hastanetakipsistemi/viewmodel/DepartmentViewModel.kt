package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Department
import com.tugfeivecek.hastanetakipsistemi.model.DepartmentResponse
import com.tugfeivecek.hastanetakipsistemi.model.Semptom
import com.tugfeivecek.hastanetakipsistemi.model.SemptomResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DepartmentViewModel : ViewModel() {

    private val apiService = HospitalAPIService()
    private val disposable = CompositeDisposable()
    val departments = MutableLiveData<List<Department>>()

    fun refreshDepartmentData(hospitalId: String) {
        getDepartmentDataFromAPI(hospitalId)
    }

    private fun getDepartmentDataFromAPI(hospitalId: String) {

        disposable.add(
            apiService.getDepartmentData(hospitalId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DepartmentResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: DepartmentResponse) {
                        Log.e("hospital name", "success")
                        departments.value = t.allDepartments
                        for (data in t.allDepartments!!) {

                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("hospital name", e.message.toString())
                        e.printStackTrace()
                    }
                })
        )
    }
}