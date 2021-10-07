package com.reakabc.applaunch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.reakabc.applaunch.R
import com.reakabc.applaunch.databinding.FragmentRegisterBinding
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.applaunch.utils.AlertDialog
import com.reakabc.applaunch.utils.SharedPreferences
import com.reakabc.applaunch.viewmodels.RegisterViewModel
import com.reakabc.applaunch.viewmodels.RegisterViewModelFactory

class RegisterFragment : Fragment() {

    lateinit var viewModel: RegisterViewModel
    lateinit var userRepository: UserRepository
    lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        preferences = SharedPreferences(requireContext())
        if (preferences.isUserLoggedIn()){
            activity?.finish()
        }

        userRepository = UserRepository(UserDatabase.getDatabase(requireContext()), requireContext())
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(userRepository)).get(RegisterViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner, {
            if (it.equals("registered")){
                preferences.putIsUserLoggedIn(true)
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            }else{
                AlertDialog.showAlert(requireContext(), "Error", it.toString())
            }
        })
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )

        binding.tvBtnLogin.setOnClickListener {
            findNavController().popBackStack()
//            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            viewModel.register(binding.etName.text.toString(), binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
        return binding.root
    }

}