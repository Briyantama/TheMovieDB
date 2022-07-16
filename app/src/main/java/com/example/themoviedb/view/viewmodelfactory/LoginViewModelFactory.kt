package com.example.themoviedb.view.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.data.local.auth.UserRepository
import com.example.themoviedb.view.viewmodel.LoginViewModel
import com.example.themoviedb.utils.UserDataManager


class LoginViewModelFactory (
    private val userRepository: UserRepository,
    private val pref: UserDataManager
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository, pref) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}