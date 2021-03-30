package com.amila.githubprofilegraphql.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amila.githubprofilegraphql.adapters.RepositoryRecyclerViewAdapter
import com.amila.githubprofilegraphql.graphql_client.GraphQLQueries
import com.amila.githubprofilegraphql.models.RepositoryDetail
import com.amila.githubprofilegraphql.models.User

class UserProfileViewModel: ViewModel() {

    private val graphQLQueries = GraphQLQueries()
    var userResults = MutableLiveData<User?>()
    var topRepositoryDetailResults = MutableLiveData<List<RepositoryDetail>?>()
    var starredRepositoryDetailResult = MutableLiveData<List<RepositoryDetail>?>()

    init {
        loadUser()
        loadTopRepositories()
        loadStarredRepositories()
    }

    fun getTopRepositories(): LiveData<List<RepositoryDetail>?>{
        return topRepositoryDetailResults
    }

    fun getStarredRepositories(): LiveData<List<RepositoryDetail>?>{
        return starredRepositoryDetailResult
    }

    private fun loadUser(){
        graphQLQueries.getUserDetails {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                userResults.value = it
            }
        }
    }

    private fun loadTopRepositories(){
        graphQLQueries.getTopRepositories {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                topRepositoryDetailResults.value = it
            }
        }
    }

    private fun loadStarredRepositories(){
        graphQLQueries.getStarredRepositories {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                starredRepositoryDetailResult.value = it
            }
        }
    }
}