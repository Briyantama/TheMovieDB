package com.example.themoviedb.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.local.auth.User
import com.example.themoviedb.data.local.auth.UserRepository
import com.example.themoviedb.utils.Event
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    private val _saved = MutableLiveData<Event<Boolean>>()
    val saved: LiveData<Event<Boolean>> get() = _saved

    fun save(
        email: String,
        username: String,
        fullname: String,
        ttl: String,
        address: String,
        password: String,
    ) {
        if (email.isEmpty() || username.isEmpty() || fullname.isEmpty() || ttl.isEmpty() || address.isEmpty() || password.isEmpty()) {
            _saved.value = Event(false)
            return
        }

        val user = User(
            email = email,
            username = username,
            fullname = fullname,
            ttl = ttl,
            address = address,
            password = password,
        )

        viewModelScope.launch {
            repository.save(user)
        }
        _saved.value = Event(true)
    }
}