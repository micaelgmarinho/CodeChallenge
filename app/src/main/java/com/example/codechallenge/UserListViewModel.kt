package com.example.codechallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.remote.UserDto
import com.example.codechallenge.remote.UserService
import com.example.codechallenge.remote.RetrofitModule
import kotlinx.coroutines.launch
import java.lang.Exception

class UserListViewModel(
    private val service: UserService
) : ViewModel() {

    private val _users = MutableLiveData<List<UserDto>>()
    val users: LiveData<List<UserDto>> = _users

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val response = service.fetchUser()
                _users.value = response.users
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

    }

    companion object {

        fun create(): UserListViewModel {
            val userService = RetrofitModule.createUsersService()
            return UserListViewModel(userService)
        }

    }
}