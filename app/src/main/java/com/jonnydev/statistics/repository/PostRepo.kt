package com.jonnydev.statistics.repository

import com.jonnydev.statistics.extension.logd
import com.jonnydev.statistics.model.PostStatistics
import com.jonnydev.statistics.model.User
import com.jonnydev.statistics.model.UserList
import com.jonnydev.statistics.network.IdRequestBody
import com.jonnydev.statistics.network.InratingClient
import com.jonnydev.statistics.network.SlugRequestBody
import retrofit2.Response

object PostRepo {
    private val mInratingClient = InratingClient.instance

    suspend fun getPostStatistics(slug: String): PostStatistics? =
        getResponseBody { mInratingClient.getPostStatistics(SlugRequestBody(slug)) }

    suspend fun getAllLikers(id: Long) : List<User>? =
        getResponseBody { mInratingClient.getAllLikers(IdRequestBody(id)) }?.data

    suspend fun getAllCommentators(id: Long) : List<User>? =
        getResponseBody { mInratingClient.getAllCommentators(IdRequestBody(id)) }?.data

    suspend fun getAllMentions(id: Long) : List<User>? =
        getResponseBody { mInratingClient.getAllMentions(IdRequestBody(id)) }?.data

    suspend fun getAllReposters(id: Long) : List<User>? =
        getResponseBody { mInratingClient.getAllReposters(IdRequestBody(id)) }?.data

    private inline fun <T> getResponseBody(getResponse: () -> Response<T>): T? = try {
        val response = getResponse()
        if (response.isSuccessful) response.body() else null
    } catch (e: Exception) {
        e.message?.let { logd(it) }
        null
    }
}
