package com.reakabc.applaunch.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.reakabc.applaunch.R
import com.reakabc.applaunch.apis.ApiClient
import com.reakabc.applaunch.apis.ApiInterface
import com.reakabc.applaunch.databinding.FragmentWeatherBinding
import com.reakabc.applaunch.repository.WeatherRepository
import com.reakabc.applaunch.utils.SharedPreferences
import com.reakabc.applaunch.viewmodels.WeatherViewModel
import com.reakabc.applaunch.viewmodels.WeatherViewModelFactory

class WeatherFragment : Fragment() {

    lateinit var viewModel: WeatherViewModel
    lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        preferences = SharedPreferences(requireContext())

        val binding: FragmentWeatherBinding = DataBindingUtil.inflate(
            inflater, com.reakabc.applaunch.R.layout.fragment_weather, container, false
        )

        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val weatherRepository =
            WeatherRepository(apiInterface, requireContext())
        viewModel = ViewModelProvider(this, WeatherViewModelFactory(weatherRepository)).get(
            WeatherViewModel::class.java
        )
        viewModel.getReport()
        viewModel.report.observe(viewLifecycleOwner, {

            binding.report = it
            Log.d("WEATHER FRAGMENT", it.toString())

        })

        binding.btnLogout.setOnClickListener {

            preferences.putIsUserLoggedIn(false)
            /*val nav = findNavController()
            nav.navigate(R.id.action_weatherFragment_to_loginFragment)
            nav.popBackStack()*/
            activity?.finish()

        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}