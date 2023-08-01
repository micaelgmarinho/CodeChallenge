package com.example.codechallenge

import com.example.codechallenge.remote.UserDto
import com.example.codechallenge.remote.UserService
import com.example.codechallenge.remote.UsersResponse
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UsersListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val service: UserService = mock()
    private lateinit var underTest: UsersListViewModelTest

    @Test
    fun `GIVEN request succeed users WHEN fetch THEN return list`() {
        runBlocking {
            val expected = listOf<UserDto>()
            UserDto(
                id = 1,
                name = "name",
                age = 1,
                email = "@gmail"
            )
            val response = UsersResponse(users = expected)
            whenever(service.fetchUser()).thenReturn(response)

            underTest = UserListViewModel(service)
            val result = underTest.userListLiveData.getOrAwaitValue()

            assert(result == expected)

        }

    }
}