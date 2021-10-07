package com.reakabc.applaunch.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.Scene
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.reakabc.applaunch.databinding.FragmentOnboardingBinding
import com.reakabc.applaunch.utils.SharedPreferences
import android.view.*
import com.reakabc.applaunch.R
import com.reakabc.applaunch.utils.AlertDialog


class OnBoardingFragment : Fragment() {

    lateinit var preferences: SharedPreferences

    private lateinit var infoOne: Scene
    private lateinit var infoTwo: Scene
    private lateinit var infoThree: Scene
    private lateinit var infoFour: Scene

    private var timer: CountDownTimer? = null

    private var index: Int = 0;

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        preferences = SharedPreferences(requireContext())
        if (preferences.isUserLoggedIn()) {
             timer = object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }
                override fun onFinish() {
                    findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
                }
            }.start()
        }

        val binding: FragmentOnboardingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_onboarding, container, false
        )

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(com.reakabc.applaunch.R.id.action_onBoardingFragment_to_loginFragment)
            timer?.cancel()
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(com.reakabc.applaunch.R.id.action_onBoardingFragment_to_registerFragment)
            timer?.cancel()
        }

        binding.tvBtnHelp.setOnClickListener {
            AlertDialog.showAlert(requireContext(), "Contact", "Write anything you want to convey!")
        }

        initializeScene(binding)
        startSlidingAnimation()

        return binding.root
    }

    private fun initializeScene(binding: FragmentOnboardingBinding) {

        infoOne = Scene.getSceneForLayout(binding.bannerLayout, com.reakabc.applaunch.R.layout.banner_1, requireContext())
        infoTwo = Scene.getSceneForLayout(binding.bannerLayout, com.reakabc.applaunch.R.layout.banner_2, requireContext())
        infoThree =
            Scene.getSceneForLayout(binding.bannerLayout, com.reakabc.applaunch.R.layout.banner_3, requireContext())
        infoFour =
            Scene.getSceneForLayout(binding.bannerLayout, com.reakabc.applaunch.R.layout.banner_4, requireContext())

        infoOne.enter()

    }

    private fun startSlidingAnimation() {
        object : CountDownTimer(8000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                when (index) {
                    0 -> {
                        val slide: Transition = Slide()
                        TransitionManager.go(infoOne, slide)
                    }
                    1 -> {
                        val slide: Transition = Slide()
                        TransitionManager.go(infoTwo, slide)
                    }
                    2 -> {
                        val slide: Transition = Slide()
                        TransitionManager.go(infoThree, slide)
                    }
                    3 -> {
                        val slide: Transition = Slide()
                        TransitionManager.go(infoFour, slide)
                    }
                }
                index++
            }

            override fun onFinish() {
                startSlidingAnimation()
                index = 0
            }

        }.start()
    }

}