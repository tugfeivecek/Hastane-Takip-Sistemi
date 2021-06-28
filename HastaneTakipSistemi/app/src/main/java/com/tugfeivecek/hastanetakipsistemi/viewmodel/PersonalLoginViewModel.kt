package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.PersonalRequest
import com.tugfeivecek.hastanetakipsistemi.model.PersonalResponse
import com.tugfeivecek.hastanetakipsistemi.model.UserRequest
import com.tugfeivecek.hastanetakipsistemi.model.UserResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import com.tugfeivecek.hastanetakipsistemi.view.personel.PersonalLoginActivity
import com.tugfeivecek.hastanetakipsistemi.view.personel.PersonelDeviceSelectionActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PersonalLoginViewModel(var context: Context) : ViewModel() {

    private val personalApiService = HospitalAPIService()
    val personals = MutableLiveData<PersonalResponse>()
    private val personalDisposable = CompositeDisposable()


    fun loginPersonalCheck(request: PersonalRequest) {
        personalDisposable.add(
            personalApiService.checkPersonal(request).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PersonalResponse>() {
                    override fun onSuccess(t: PersonalResponse) {

                        Log.e("PERSONAL", t.error_message.toString())
                        if (t.error != true) {

                            personals.value = t
                            val intent =
                                Intent(context, PersonelDeviceSelectionActivity::class.java)
                            context.startActivity(intent)

                            Toast.makeText(
                                context,
                                "Üye girişi başarılı!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            val intent =
                                Intent(context, PersonalLoginActivity::class.java)
                            context.startActivity(intent)
                            Toast.makeText(
                                context,
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