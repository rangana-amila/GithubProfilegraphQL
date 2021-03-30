package com.amila.githubprofilegraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.amila.githubprofilegraphql.adapters.RepositoryRecyclerViewAdapter
import com.amila.githubprofilegraphql.databinding.ActivityMainBinding
import com.amila.githubprofilegraphql.graphql_client.GraphQLQueries
import com.amila.githubprofilegraphql.models.RepositoryDetail
import com.amila.githubprofilegraphql.viewmodels.UserProfileViewModel

class MainActivity : AppCompatActivity() {

    lateinit var userProfileViewModel: UserProfileViewModel
    lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()

        setTopRepoAdapter()
        setStarredRepoAdapter()
    }

    private fun setTopRepoAdapter(){
        mainActivityBinding.recyclerViewTopRepos.hasFixedSize()
        mainActivityBinding.recyclerViewTopRepos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var topRepoAdapter = RepositoryRecyclerViewAdapter(ArrayList())
        mainActivityBinding.recyclerViewTopRepos.adapter = topRepoAdapter
//            mainActivityBinding.topRepoAdapter = topRepoAdapter

        userProfileViewModel.getTopRepositories().observe(this,
            {
                    t -> topRepoAdapter.setData(t!!)
            })
    }

    private fun setStarredRepoAdapter(){
        mainActivityBinding.recyclerViewStarredRepos.hasFixedSize()
        mainActivityBinding.recyclerViewStarredRepos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var starredRepoAdapter = RepositoryRecyclerViewAdapter(ArrayList())
        mainActivityBinding.recyclerViewStarredRepos.adapter = starredRepoAdapter
//            mainActivityBinding.topRepoAdapter = topRepoAdapter

        userProfileViewModel.getStarredRepositories().observe(this,
            {
                    t -> starredRepoAdapter.setData(t!!)
            })
    }

    private fun initDataBinding(){
        mainActivityBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        userProfileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        mainActivityBinding.viewModel = userProfileViewModel
        mainActivityBinding.lifecycleOwner = this
    }
}