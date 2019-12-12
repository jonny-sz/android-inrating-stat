package com.jonnydev.statistics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonnydev.statistics.databinding.UserListItemBinding
import com.jonnydev.statistics.model.User
import kotlin.properties.Delegates.observable

class UserListAdapter :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>(),
    AutoUpdatableAdapter {

    var users by observable(mutableListOf<User>()) { _, oldUsers, newUsers ->
        autoNotify(oldUsers, newUsers) { oldUser, newUser -> oldUser.id == newUser.id }
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(layoutInflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun getItemId(position: Int) = users[position].id

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.bind(user)
    }

    class UserViewHolder(
        private val binding: UserListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
        }
    }
}
