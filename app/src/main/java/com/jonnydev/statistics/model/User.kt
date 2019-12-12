package com.jonnydev.statistics.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Long,
    val nickname: String,

    @SerializedName("avatar_image")
    val imgUrl: UserImg
)

data class UserList(
    val data: List<User>
)

data class UserImg(
    @SerializedName("url_small")
    val imgUrl: String
) {
    override fun toString() = imgUrl
}
