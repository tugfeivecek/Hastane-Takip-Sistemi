package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Device
import com.tugfeivecek.hastanetakipsistemi.model.DeviceResponse
import com.tugfeivecek.hastanetakipsistemi.model.Semptom
import com.tugfeivecek.hastanetakipsistemi.model.SemptomResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DeviceViewModel : ViewModel() {

    private val apiService = HospitalAPIService()
    private val disposable = CompositeDisposable()
    val devices = MutableLiveData<List<Device>>()
    val devicesDepartment = MutableLiveData<List<Device>>()

    fun refreshDeviceDataByDeviceId(deviceId: String) {
        getDeviceByDeviceId(deviceId)
    }

    fun refreshDeviceDataByDepartmentId(departmentId: String) {
        getDeviceByDepartmentId(departmentId)
    }

    private fun getDeviceByDeviceId(deviceId: String) {

        disposable.add(
            apiService.getDeviceDataByDeviceId(deviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DeviceResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: DeviceResponse) {

                        devices.value = t.allDevices
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun getDeviceByDepartmentId(deparmentId: String) {

        disposable.add(
            apiService.getDeviceDataByDepartmentId(deparmentId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DeviceResponse>() {
                    //bir hata olursa ne yapıcaz hata olmazsa ne yapicaz
                    override fun onSuccess(t: DeviceResponse) {

                        devicesDepartment.value = t.allDevices
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}