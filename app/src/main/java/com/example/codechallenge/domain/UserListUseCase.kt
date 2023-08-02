package com.example.codechallenge.domain

import com.example.codechallenge.remote.UserDto
import com.example.codechallenge.remote.UserService

class UserListUseCase(private val service: UserService) {
    suspend fun getList(): List<List<UserDto>> {
        val list = service.fetchUser().users

        return list.chunked(10)

    }

}