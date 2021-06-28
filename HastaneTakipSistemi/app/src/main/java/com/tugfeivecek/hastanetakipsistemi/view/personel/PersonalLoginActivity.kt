package com.tugfeivecek.hastanetakipsistemi.view.personel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonalLoginBinding
import com.tugfeivecek.hastanetakipsistemi.model.PersonalRequest
import com.tugfeivecek.hastanetakipsistemi.model.UserRequest
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LoginViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.PersonalLoginViewModel
import java.util.regex.Pattern

class PersonalLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalLoginBinding
    var fragment: Fragment? = null
    private lateinit var personelLoginViewModel: PersonalLoginViewModel
    private var durum: Boolean = false
    private var adsoyad: String = ""

    private val NUMBER_PATTERN = Pattern.compile("^(0|[1-9][0-9]*)\$")
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" + "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                //"(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{8,}" +  //at least 8 characters
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalLoginBinding.inflate(layoutInflater)

        personelLoginViewModel = PersonalLoginViewModel(this)
        setContentView(binding.root)

        val sp = getSharedPreferences("PersonalGirisBilgi", Context.MODE_PRIVATE)
        durum = sp.getBoolean("personalGiris", false)
        adsoyad = sp.getString("personalAdSoyad", "Ad Soyad Yok").toString()

        if (durum == true) {
            val intent =
                Intent(this, PersonelDeviceSelectionActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            checkAuthentication()
        }



    }

    fun validateUserName(): Boolean {
        var getUserName = binding.tlPersonalUseername.editText?.text.toString().trim()

        if (getUserName.isEmpty()) {
            binding.tlPersonalUseername.error = "Kullanıcı Adı Boş Bırakılamaz"
            return false
        } else if (NUMBER_PATTERN.matcher(getUserName).matches()
        ) {
            binding.tlPersonalUseername.error = "Geçerli bir kullanıcı adı giriniz"
            return false
        } else {
            binding.tlPersonalUseername.error = null
            return true
        }

    }

    private fun validatePersonalPassword(): Boolean {

        var getPassword = binding.tlPersonalPassword.editText?.text.toString().trim()
        if (getPassword.isEmpty()) {
            binding.tlPersonalPassword.error = "Şifre Boş Bırakılamaz"
            return false
        } else if (!PASSWORD_PATTERN.matcher(getPassword).matches()) {
            binding.tlPersonalPassword.error = "Geçerli bir şifre giriniz!"
            return false
        } else {
            binding.tlPersonalPassword.error = null
            return true
        }

    }

    fun checkAuthentication() {
        if (!validateUserName() || !validatePersonalPassword()) {
            return
        } else {


            var userName = binding.etPersonalUseername.text.toString()
            var password = binding.etPersonalPassword.text.toString()

            var request = PersonalRequest()
            request.userName = userName
            request.password = password
            personelLoginViewModel.loginPersonalCheck(request)
            observePersonalData()

        }

    }

    private fun observePersonalData() {
        personelLoginViewModel.personals.observe(this, Observer { user ->
            user.let {
                if (user.error != true) {
                    val sp = getSharedPreferences("PersonalGirisBilgi", MODE_PRIVATE)
                    val editor = sp.edit()
                    editor.putBoolean("personalGiris", true)
                    editor.putString("personalAdSoyad", user.personal?.name)
                    editor.putString("personelHospitalId", user.personal?.hospitalId)
                    editor.apply()
                }
                Log.e("Identity Number: ", user.personal?.userName.toString())
                Log.e("Şifre: ", user.personal?.userName.toString())
            }
        })
    }
}