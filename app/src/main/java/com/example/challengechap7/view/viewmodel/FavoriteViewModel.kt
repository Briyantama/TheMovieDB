package com.example.challengechap7.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechap7.data.local.favorite.MovieEntity
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repository: MovieRepository) : ViewModel() {

    private var _getListFavorite = MutableLiveData<List<MovieEntity>>()
    val getListFavorite: LiveData<List<MovieEntity>> get() = _getListFavorite

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getUser() {
        viewModelScope.launch {
            _getListFavorite.postValue(repository.getFavoriteUser())
        }
    }

}