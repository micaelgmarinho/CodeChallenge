package com.example.codechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.remote.UserDto

class MainActivity : AppCompatActivity() {

    private lateinit var usersParent: LinearLayout
    private lateinit var adapter: UserListAdapter

    private val viewModel by lazy {
        UserListViewModel.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersParent = findViewById(R.id.users_parent)
        adapter = UserListAdapter(users = listOf())


        viewModel.users.observe(this) { chunkedList ->
            val users: List<List<User>> = chunkedList.map { usersDto ->
                usersDto.map { userDto ->
                    User(
                        id = userDto.id,
                        name = userDto.name,
                        age = userDto.age,
                        email = userDto.email
                    )
                }
            }

            usersParent.removeAllViews()

            users.forEach { list ->
                val recyclerView = RecyclerView(this)
                recyclerView.layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
                recyclerView.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                usersParent.addView(recyclerView)
                val adapter: UserListAdapter = UserListAdapter(list)
                recyclerView.adapter = adapter
            }
        }
    }
}