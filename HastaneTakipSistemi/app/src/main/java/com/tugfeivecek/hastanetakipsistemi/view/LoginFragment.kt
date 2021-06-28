package com.tugfeivecek.hastanetakipsistemi.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentLoginBinding
import com.tugfeivecek.hastanetakipsistemi.model.UserRequest
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LoginViewModel
import java.util.regex.Pattern


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

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
        binding = FragmentLoginBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loginViewModel = LoginViewModel(this)
        binding.btnLogin.setOnClickListener {
            checkAuthentication()
        }
        binding.tvRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            view?.let {
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun validateTC(): Boolean {
        var getTc = binding.tlLoginTc.editText?.text.toString().trim()

        if (getTc.isEmpty()) {
            binding.tlLoginTc.error = "TC Boş Bırakılamaz"
            return false
        } else if (!NUMBER_PATTERN.matcher(getTc).matches()
        ) {
            binding.tlLoginTc.error = "Geçerli bir tc kimlik adresi giriniz"
            return false
        } else {
            binding.tlLoginTc.error = null
            return true
        }

    }

    fun validatePassword(): Boolean {

        var getPassword = binding.tlLoginSifre.editText?.text.toString().trim()
        if (getPassword.isEmpty()) {
            binding.tlLoginSifre.error = "Şifre Boş Bırakılamaz"
            return false
        } else if (!PASSWORD_PATTERN.matcher(getPassword).matches()) {
            binding.tlLoginSifre.error = "Geçerli bir şifre giriniz!"
            return false
        } else {
            binding.tlLoginSifre.error = null
            return true
        }

    }

    fun checkAuthentication() {
        if (!validateTC() || !validatePassword()) {
            return
        } else {


            var identityNumber = binding.etLoginTc.text.toString()
            var password = binding.etLoginSifre.text.toString()


            var request = UserRequest()
            request.identityNumber = identityNumber
            request.password = password
            loginViewModel.loginUserCheck(request)
            observeLoginData()

        }

    }

    private fun observeLoginData() {
        loginViewModel.users.observe(viewLifecycleOwner, Observer { user ->
            user.let {
                if (user.error != true) {
                    val sp =
                        requireActivity().getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)
                    val editor = sp.edit()
                    editor.putBoolean("girisDurum", true)
                    editor.putString("adsoyad", user.user?.userName)
                    editor.apply()
                }
                Log.e("Identity Number: ", user.user?.identityNumber.toString())
                Log.e("Şifre: ", user.user?.userName.toString())
            }
        })
    }
}