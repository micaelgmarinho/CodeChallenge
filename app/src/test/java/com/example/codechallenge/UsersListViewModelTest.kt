package com.example.codechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codechallenge.domain.UserListUseCase
import com.example.codechallenge.remote.UserDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UsersListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: UserListUseCase = mock()
    private lateinit var underTest: UserListViewModel

    @Test
    fun `GIVEN request succeed users WHEN fetch THEN return list`() {
        runBlocking {
            val list01 = listOf<UserDto>()
            UserDto(
                id = 1,
                name = "name",
                age = 1,
                email = "@gmail"
            )
            val list02 = listOf<UserDto>()
            UserDto(
                id = 2,
                name = "name2",
                age = 2,
                email = "@gmail2"
            )

            val expected = listOf(list01, list02)

            whenever(useCase.getList()).thenReturn(expected)

            underTest = UserListViewModel(useCase)
            val result = underTest.users.getOrAwaitValue()

            assert(result == expected)

        }

    }
}