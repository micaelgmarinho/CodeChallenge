package com.example.codechallenge.remote

data class UsersResponse(
    val users: List<UserDto>
)
data class UserDto(
    val id: Int,
    val name: String,
    val age: Int,
    val email: String
)