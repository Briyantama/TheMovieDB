package com.example.challengechap7.view.viewmodel

import androidx.lifecycle.*
import com.example.challengechap7.data.local.auth.User
import com.example.challengechap7.data.local.auth.UserRepository
import com.example.challengechap7.resource.Resource
import com.example.challengechap7.utils.UserDataManager
import kotlinx.coroutines.launch

class LoginViewModel (
    private val repository: UserRepository,
    private val pref: UserDataManager
) : ViewModel() {

    private var _loginStatus = MutableLiveData<Resource<User>>()
    val loginStatus: LiveData<Resource<User>> get() = _loginStatus


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginStatus.postValue(Resource.loading(null))
            try {
                val data = repository.verifyLogin(email, password)
                _loginStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _loginStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

    fun saveUserDataStore(status: Boolean, id: Int) {
        viewModelScope.launch {
            pref.saveUser(status, id)
        }
    }


    fun getStatus(): LiveData<Boolean>{
        return pref.getStatus().asLiveData()
    }

}