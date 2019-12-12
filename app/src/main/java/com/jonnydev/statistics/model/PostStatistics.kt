package com.jonnydev.statistics.model

import com.google.gson.annotations.SerializedName

data class PostStatistics(
    @SerializedName("id")
    val postId: Long,

    @SerializedName("views_count")
    val viewsCount: Long,

    @SerializedName("likes_count")
    val likesCount: Long,

    @SerializedName("comments_count")
    val commentsCount: Long,

    val attachments: PostStatAttachments,

    @SerializedName("reposts_count")
    val repostsCount: Long,

    @SerializedName("bookmarks_count")
    val bookmarksCount: Long
) {
    val mentionsCount get() = attachments.images[0].mentionsCount
}

data class PostStatAttachments(
    val images: List<PostImageStat>
)

data class PostImageStat(
    @SerializedName("mentioned_users_count")
    val mentionsCount: Long
)
