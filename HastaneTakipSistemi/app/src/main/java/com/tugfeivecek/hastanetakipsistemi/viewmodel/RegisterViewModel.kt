package com.tugfeivecek.hastanetakipsistemi.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.RegisterRequest
import com.tugfeivecek.hastanetakipsistemi.model.RegisterResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(var fragment: Fragment) : ViewModel() {

    private val userApiService = HospitalAPIService()
    val registers = MutableLiveData<RegisterResponse>()
    private val registerDisposable = CompositeDisposable()

    fun registerUser(registerRequest: RegisterRequest) {
        registerDisposable.add(
            userApiService.registerUser(registerRequest).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RegisterResponse>() {
                    override fun onSuccess(t: RegisterResponse) {

                        if (t.error != true) {
                            registers.value = t
                            fragment.findNavController()
                                .navigate(R.id.action_global_to_main_fragment)
                            Toast.makeText(
                                fragment.context,
                                "Üye kaydı başarılı!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            fragment.findNavController().navigate(R.id.registerFragment)
                            val builder = AlertDialog.Builder(fragment.requireContext())
                            builder.setTitle("Bilgi")
                            builder.setMessage("Üye kaydı başarılı değil!")


                            builder.setPositiveButton("Yeniden Dene") { dialog, which ->
                                dialog.dismiss()

                            }

                            builder.setNegativeButton("Vazgeç") { dialog, which ->
                                fragment.findNavController().navigate(R.id.profileFragment)
                            }


                            builder.show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Failure Error", "${e.message}")
                    }

                })
        )

    }


}