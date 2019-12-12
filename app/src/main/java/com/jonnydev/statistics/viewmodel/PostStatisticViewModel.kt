package com.jonnydev.statistics.viewmodel

import androidx.databinding.ObservableLong
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonnydev.statistics.model.PostStatistics
import com.jonnydev.statistics.model.User
import com.jonnydev.statistics.repository.PostRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//const val POST_ID: Long = 2720
const val SLUG: String = "LeBxOWT5zSemiSvkuqBLXFjXlaA0bJlX"

class PostStatisticViewModel : ViewModel() {
    val viewsCount = ObservableLong()
    val likesCount = ObservableLong()
    val commentatorsCount = ObservableLong()
    val mentionsCount = ObservableLong()
    val repostsCount = ObservableLong()
    val bookmarksCount = ObservableLong()

    private val _likers = MutableLiveData<List<User>>()
    private val _commentators = MutableLiveData<List<User>>()
    private val _reposters = MutableLiveData<List<User>>()
    private val _mentions = MutableLiveData<List<User>>()

    val likers: LiveData<List<User>> = _likers
    val commentators: LiveData<List<User>> = _commentators
    val reposters: LiveData<List<User>> = _reposters
    val mentions: LiveData<List<User>> = _mentions

    init {
        fetchPostStatistics()
    }

    private fun fetchPostStatistics() = viewModelScope.launch(Dispatchers.IO) {
        val postStatistics = withContext(Dispatchers.IO) { PostRepo.getPostStatistics(SLUG) }

        postStatistics?.let { stat ->
            if (stat.viewsCount > 0) viewsCount.set(stat.viewsCount)

            initLikers(stat, this)
            initCommentators(stat, this)
            initMentions(stat, this)
            initReposters(stat, this)

            if (stat.bookmarksCount > 0) bookmarksCount.set(stat.bookmarksCount)
        }
    }

    private fun initLikers(stat: PostStatistics, scope: CoroutineScope) = scope.launch {
        if (stat.likesCount > 0) {
            initUsers(PostRepo.getAllLikers(stat.postId), likesCount, _likers)
        }
    }

    private fun initCommentators(stat: PostStatistics, scope: CoroutineScope) = scope.launch {
        if (stat.commentsCount > 0) {
            initUsers(PostRepo.getAllCommentators(stat.postId), commentatorsCount, _commentators)
        }
    }

    private fun initMentions(stat: PostStatistics, scope: CoroutineScope) = scope.launch {
        if (stat.mentionsCount > 0) {
            initUsers(PostRepo.getAllMentions(stat.postId), mentionsCount, _mentions)
        }
    }

    private fun initReposters(stat: PostStatistics, scope: CoroutineScope) = scope.launch {
        if (stat.repostsCount > 0) {
            initUsers(PostRepo.getAllReposters(stat.postId), repostsCount, _reposters)
        }
    }

    private fun initUsers(
        users: List<User>?,
        count: ObservableLong,
        liveData: MutableLiveData<List<User>>
    ) {
        users?.let {
            count.set(it.size.toLong())
            liveData.postValue(it)
        }
    }
}
