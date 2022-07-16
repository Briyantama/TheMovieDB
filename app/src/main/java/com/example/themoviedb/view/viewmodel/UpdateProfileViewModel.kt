package com.example.themoviedb.view.viewmodel

import androidx.lifecycle.*
import com.example.themoviedb.utils.Event
import com.example.themoviedb.data.local.auth.User
import com.example.themoviedb.data.local.auth.UserRepository
import com.example.themoviedb.resource.Resource
import com.example.themoviedb.utils.UserDataManager
import kotlinx.coroutines.launch

class UpdateProfileViewModel(
    private val pref: UserDataManager,
    private val userRepository: UserRepository
    ) : ViewModel() {

    private val _saved = MutableLiveData<Event<Boolean>>()
    val saved: LiveData<Event<Boolean>> get() = _saved
    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    private var _userData = MutableLiveData<Resource<User>>()
    val userData: LiveData<Resource<User>> get() = _userData

    fun update(user: User
    ) {
        if (user.email.isEmpty() || user.username.isEmpty() || user.fullname.isEmpty() || user.ttl.isEmpty() || user.address.isEmpty() || user.password.isEmpty() || user.id == 0 ) {
            _saved.value = Event(false)
        } else {
            _saved.value = Event(true)
            viewModelScope.launch {
                userRepository.update(user)
            }
        }
    }
    fun clearDataUser() {
        viewModelScope.launch {
            pref.logoutUser()
        }
    }
    fun userData(id: Int) {
        viewModelScope.launch {
            _userData.postValue(Resource.loading(null))
            try {
                val data = userRepository.getUser(id)
                _userData.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _userData.postValue(Resource.error(null, exception.message!!))
            }
        }
    }
    fun getIdUser(): LiveData<Int>{
        return pref.getId().asLiveData()
    }
}