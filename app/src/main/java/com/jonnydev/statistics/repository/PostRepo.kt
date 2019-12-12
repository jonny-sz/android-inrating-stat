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

    suspend fun getPostStatistics(slug: String): PostStatistics? {
        return try {
            val response = mInratingClient.getPostStatistics(SlugRequestBody(slug))

            when {
                response.isSuccessful -> response.body()
                else -> null
            }
        } catch (e: Exception) {
            e.message?.let { logd(it) }
            null
        }
    }

    suspend fun getAllLikers(id: Long) : List<User>? {
        return try {
            val response = mInratingClient.getAllLikers(IdRequestBody(id))

            getResponseBody(response)
        } catch (e: Exception) {
            e.message?.let { logd(it) }
            null
        }
    }

    suspend fun getAllCommentators(id: Long) : List<User>? {
        return try {
            val response = mInratingClient.getAllCommentators(IdRequestBody(id))

            getResponseBody(response)
        } catch (e: Exception) {
            e.message?.let { logd(it) }
            null
        }
    }

    suspend fun getAllMentions(id: Long) : List<User>? {
        return try {
            val response = mInratingClient.getAllMentions(IdRequestBody(id))

            getResponseBody(response)
        } catch (e: Exception) {
            e.message?.let { logd(it) }
            null
        }
    }

    suspend fun getAllReposters(id: Long) : List<User>? {
        return try {
            val response = mInratingClient.getAllReposters(IdRequestBody(id))

            getResponseBody(response)
        } catch (e: Exception) {
            e.message?.let { logd(it) }
            null
        }
    }

    private fun getResponseBody(response: Response<UserList>): List<User>? {
        return when {
            response.isSuccessful -> response.body()?.data
            else -> null
        }
    }
}
