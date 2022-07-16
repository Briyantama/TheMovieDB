package com.example.themoviedb.view.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.themoviedb.view.viewmodel.HomeViewModel
import com.example.themoviedb.utils.UserDataManager

class HomeViewModelFactory (
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository,
    private val pref: UserDataManager
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(movieRepository, userRepository, pref) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}