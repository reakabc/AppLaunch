package com.reakabc.applaunch.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.reakabc.applaunch.R
import com.reakabc.applaunch.databinding.FragmentLoginBinding
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.applaunch.utils.AlertDialog
import com.reakabc.applaunch.utils.SharedPreferences
import com.reakabc.applaunch.viewmodels.LoginViewModel
import com.reakabc.applaunch.viewmodels.LoginViewModelFactory

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel
    lateinit var userRepository: UserRepository
    lateinit var preferences: SharedPreferences

    lateinit var binding: FragmentLoginBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preferences = SharedPreferences(requireContext())
        if (preferences.isUserLoggedIn()){
            val nav = findNavController()
            nav.navigate(R.id.action_loginFragment_to_homeFragment)
//            nav.popBackStack()
        }

        userRepository = UserRepository(UserDatabase.getDatabase(requireContext()), requireContext())
        viewModel = ViewModelProvider(this, LoginViewModelFactory(userRepository)).get(
            LoginViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner, {
            if (it.equals("loggedin")){
                preferences.putIsUserLoggedIn(true)
                preferences.putUserEmail(binding.etEmail.text.toString())
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                AlertDialog.showAlert(requireContext(), "Error", it.toString())
            }
        })

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

        binding.tvBtnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }

}