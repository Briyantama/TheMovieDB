package com.example.challengechap7.view.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challengechap7.data.local.auth.UserRepository
import com.example.challengechap7.utils.UserDataManager
import com.example.challengechap7.view.viewmodel.UpdateProfileViewModel

class UpdateViewModelFactory (
    private val userRepository: UserRepository,
    private val pref: UserDataManager
    ) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(UpdateProfileViewModel::class.java) -> {
                UpdateProfileViewModel(pref, userRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}