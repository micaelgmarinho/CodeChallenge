package com.example.codechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.remote.UserDto

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter

    private val viewModel by lazy {
        UserListViewModel.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_users)
        adapter = UserListAdapter(users = listOf())

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val rvUsers: RecyclerView = findViewById(R.id.rv_users)

        viewModel.users.observe(this) { usersDto ->
            val users = usersDto.map { userDto ->
                User(
                    id = userDto.id,
                    name = userDto.name,
                    age = userDto.age,
                    email = userDto.email

                )
            }
            val adapter: UserListAdapter = UserListAdapter(users)

            rvUsers.adapter = adapter
        }

    }
}