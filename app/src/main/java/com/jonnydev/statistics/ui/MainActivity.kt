package com.jonnydev.statistics.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonnydev.statistics.R
import com.jonnydev.statistics.adapter.UserListAdapter
import com.jonnydev.statistics.databinding.ActivityMainBinding
import com.jonnydev.statistics.model.User
import com.jonnydev.statistics.viewmodel.PostStatisticViewModel
import kotlinx.android.synthetic.main.commentators_category.*
import kotlinx.android.synthetic.main.likes_category.*
import kotlinx.android.synthetic.main.mentions_category.*
import kotlinx.android.synthetic.main.reposts_category.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel by lazy {
        ViewModelProvider(this)[PostStatisticViewModel::class.java]
    }
    private val mLikersAdapter = UserListAdapter()
    private val mCommentatorsAdapter = UserListAdapter()
    private val mMentionsAdapter = UserListAdapter()
    private val mRepostersAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initRecycleViews()
        fillAdapters()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel
    }

    private fun initRecycleViews() {
        likers_rv.init(mLikersAdapter)
        commentators_rv.init(mCommentatorsAdapter)
        mentions_rv.init(mMentionsAdapter)
        reposts_rv.init(mRepostersAdapter)
    }

    private fun RecyclerView.init(userListAdapter: UserListAdapter) {
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        adapter = userListAdapter
    }

    private fun fillAdapters() {
        mViewModel.run {
            likers.fillAdapter(mLikersAdapter)
            commentators.fillAdapter(mCommentatorsAdapter)
            mentions.fillAdapter(mMentionsAdapter)
            reposters.fillAdapter(mRepostersAdapter)
        }
    }

    private fun LiveData<List<User>>.fillAdapter(adapter: UserListAdapter) {
        observe(this@MainActivity, Observer { userList ->
            userList?.let { adapter.users = it.toMutableList() }
        })
    }
}
