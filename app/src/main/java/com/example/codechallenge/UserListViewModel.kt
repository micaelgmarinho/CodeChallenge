package com.example.codechallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.domain.UserListUseCase
import com.example.codechallenge.remote.UserDto
import com.example.codechallenge.remote.UserService
import com.example.codechallenge.remote.RetrofitModule
import kotlinx.coroutines.launch
import java.lang.Exception

class UserListViewModel(
    private val useCase: UserListUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<List<UserDto>>>()
    val users: LiveData<List<List<UserDto>>> = _users

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val response = useCase.getList()
                _users.value = response
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

    }

    companion object {

        fun create(): UserListViewModel {
            val userService = RetrofitModule.createUsersService()
            val useCase = UserListUseCase(userService)
            return UserListViewModel(useCase)
        }

    }
}