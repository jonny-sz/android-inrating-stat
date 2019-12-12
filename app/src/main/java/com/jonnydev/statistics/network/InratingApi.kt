package com.jonnydev.statistics.network

import com.jonnydev.statistics.model.PostStatistics
import com.jonnydev.statistics.model.UserList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface InratingApi {
    @POST("get")
    suspend fun getPostStatistics(@Body body: SlugRequestBody) : Response<PostStatistics>

    @POST("likers/all")
    suspend fun getAllLikers(@Body body: IdRequestBody) : Response<UserList>

    @POST("commentators/all")
    suspend fun getAllCommentators(@Body body: IdRequestBody) : Response<UserList>

    @POST("mentions/all")
    suspend fun getAllMentions(@Body body: IdRequestBody) : Response<UserList>

    @POST("reposters/all")
    suspend fun getAllReposters(@Body body: IdRequestBody) : Response<UserList>
}
