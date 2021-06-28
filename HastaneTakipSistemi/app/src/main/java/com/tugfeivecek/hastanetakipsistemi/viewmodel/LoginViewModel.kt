package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.UserRequest
import com.tugfeivecek.hastanetakipsistemi.model.UserResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class LoginViewModel(var fragment: Fragment) : ViewModel() {

    private val userApiService = HospitalAPIService()
    val users = MutableLiveData<UserResponse>()
    private val loginDisposable = CompositeDisposable()


    fun loginUserCheck(request: UserRequest) {
        loginDisposable.add(
            userApiService.checkUser(request).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserResponse>() {
                    override fun onSuccess(t: UserResponse) {

                        if (t.error != true) {

                            users.value = t
                            fragment.findNavController()
                                .navigate(R.id.action_global_to_main_fragment)

                            Toast.makeText(
                                fragment.context,
                                "Üye girişi başarılı!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            fragment.findNavController().navigate(R.id.registerFragment)
                            Toast.makeText(
                                fragment.context,
                                "Hatalı üye girişi!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Failure Error", "${e.message}")
                    }

                })
        )

    }


}