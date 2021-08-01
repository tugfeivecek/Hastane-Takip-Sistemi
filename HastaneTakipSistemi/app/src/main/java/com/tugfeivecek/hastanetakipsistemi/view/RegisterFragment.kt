package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentRegisterBinding
import com.tugfeivecek.hastanetakipsistemi.model.RegisterRequest
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LoginViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.RegisterViewModel
import java.util.*
import java.util.regex.Pattern


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
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
    private val NUMBER_PATTERN = Pattern.compile("^(0|[1-9][0-9]*)\$")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel = RegisterViewModel(this)
        binding.tvSaveUser.setOnClickListener {
            checkAuthentication()
        }
    }

    private fun checkAuthentication() {
        if (!validateTC() || !validatePassword() || !validateEmail() || !validateName()) {
            return
        } else {


            var identityNumber = binding.etRegisterTc.text.toString()
            var password = binding.etRegisterSifre.text.toString()
            var email = binding.etRegisterEposta.text.toString()
            var name = binding.etRegisterName.text.toString()


            var request = RegisterRequest()
            request.identityNumber = identityNumber
            request.password = password
            request.mail = email
            request.name = name
            request.userName = name.replace(" ", "").trim() + UUID.randomUUID()

            registerViewModel.registerUser(request)
            observeRegisterData()


        }
    }

    private fun validateTC(): Boolean {
        var getTc = binding.tlRegisterTc.editText?.text.toString().trim()

        if (getTc.isEmpty()) {
            binding.tlRegisterTc.error = "TC Boş Bırakılamaz"
            return false
        } else if (!NUMBER_PATTERN.matcher(getTc).matches()
        ) {
            binding.tlRegisterTc.error = "Geçerli bir tc kimlik adresi giriniz"
            return false
        } else {
            binding.tlRegisterTc.error = null
            return true
        }

    }

    private fun validateName(): Boolean {

        var getPassword = binding.tlRegisterName.editText?.text.toString().trim()
        if (getPassword.isEmpty()) {
            binding.tlRegisterName.error = "Ad Soyad Boş Bırakılamaz"
            return false
        } else if (NUMBER_PATTERN.matcher(getPassword).matches()) {
            binding.tlRegisterName.error = "Geçerli bir ad soyad giriniz!"
            return false
        } else {
            binding.tlRegisterName.error = null
            return true
        }

    }

    private fun validateEmail(): Boolean {

        var getPassword = binding.tlRegisterEposta.editText?.text.toString().trim()
        if (getPassword.isEmpty()) {
            binding.tlRegisterEposta.error = "E-mail Boş Bırakılamaz"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getPassword).matches()) {
            binding.tlRegisterEposta.error = "Geçerli bir e-mail giriniz!"
            return false
        } else {
            binding.tlRegisterEposta.error = null
            return true
        }

    }

    private fun validatePassword(): Boolean {

        var getPassword = binding.tlRegisterSifre.editText?.text.toString().trim()
        if (getPassword.isEmpty()) {
            binding.tlRegisterSifre.error = "Şifre Boş Bırakılamaz"
            return false
        } else if (!PASSWORD_PATTERN.matcher(getPassword).matches()) {
            binding.tlRegisterSifre.error = "Geçerli bir şifre giriniz!"
            return false
        } else {
            binding.tlRegisterSifre.error = null
            return true
        }

    }

    private fun observeRegisterData() {
        registerViewModel.registers.observe(viewLifecycleOwner, Observer { register ->
            register.let {
                Log.e("", register.user?.userName.toString())
                Log.e("", register.user?.date.toString())

            }
        })
    }


}