package com.tugfeivecek.hastanetakipsistemi.view.personel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentPersonalLoginBinding
import com.tugfeivecek.hastanetakipsistemi.view.MainFragmentDirections

class PersonalLoginFragment : Fragment() {
    private lateinit var binding: FragmentPersonalLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = PersonalLoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalLoginBinding.inflate(layoutInflater)
        binding.btnLogin.setOnClickListener {
            val action =
                PersonalLoginFragmentDirections.actionPersonalLoginFragmentToPersonelDeviceSelectionFragment()

            view.let {
                Navigation.findNavController(it!!).navigate(action)
            }
        }

        return binding.root
    }
}