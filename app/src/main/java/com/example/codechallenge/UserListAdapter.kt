package com.example.codechallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserListAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    // Tamanho da minha lista
    override fun getItemCount(): Int {
        return users.size
    }


    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvId = view.findViewById<TextView>(R.id.tv_id)
        private val tvName = view.findViewById<TextView>(R.id.tv_name)
        private val tvAge = view.findViewById<TextView>(R.id.tv_age)
        private val tvEmail = view.findViewById<TextView>(R.id.tv_email)

        fun bind(user: User) {

            tvId.text = user.id.toString()
            tvName.text = user.name
            tvAge.text = user.age.toString()
            tvEmail.text = user.email

        }
    }

}
/*class UserListAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    // Tamanho da minha lista
    override fun getItemCount(): Int {
        return users.size
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvId = view.findViewById<TextView>(R.id.tv_id)
        private val tvName = view.findViewById<TextView>(R.id.tv_name)
        private val tvAge = view.findViewById<TextView>(R.id.tv_age)
        private val tvEmail = view.findViewById<TextView>(R.id.tv_email)

        fun bind(user: User) {

            tvId.text = user.id.toString()
            tvName.text = user.name
            tvAge.text = user.age.toString()
            tvEmail.text = user.email

        }
    }

}*/

