package com.example.challengechap7.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import com.example.challengechap7.R
import com.example.challengechap7.databinding.FragmentSplashBinding
import com.example.challengechap7.utils.UserDataManager

class Splash : Fragment() {
    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var userManager : UserDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserDataManager(requireContext())
        Handler(Looper.getMainLooper()).postDelayed({
            //check if user was logging in or not
            userManager.getStatus().asLiveData().observe(viewLifecycleOwner) {
                if (it == true) {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_splash_to_home)
                } else {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_splash_to_login)
                }
            }
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}