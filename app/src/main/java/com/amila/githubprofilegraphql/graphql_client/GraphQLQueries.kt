package com.amila.githubprofilegraphql.graphql_client

import android.util.Log
import com.amila.githubprofilegraphql.models.RepoOwner
import com.amila.githubprofilegraphql.models.RepositoryDetail
import com.amila.githubprofilegraphql.models.User
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class GraphQLQueries {

    var client = GraphQLClient.apolloClient

    fun getUserDetails(completion: (result: User?) -> Unit) {

        client.query(GetUserQuery    //From the auto generated class
            .builder()
            .build())
            .enqueue(object : ApolloCall.Callback<GetUserQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.d(" Error:" , e.message.toString())
//                    completion(Pair(null, Error(e.message)))
                }
                override fun onResponse(response: Response<GetUserQuery.Data>) {
                    Log.d(" Repository; " , response.data?.user().toString())
                    completion(User(response.data?.user()?.name(), response.data?.user()?.login(),
                        response.data?.user()?.email(),
                        response.data?.user()?.avatarUrl() as String?,
                        response.data?.user()?.followers()?.totalCount().toString(),
                        response.data?.user()?.following()?.totalCount().toString()))
                }
            })
    }

    fun getTopRepositories(completion: (result: List<RepositoryDetail>?) -> Unit){
        client.query(GetTopRepositoriesQuery   //From the auto generated class
            .builder()
            .build())
            .enqueue(object : ApolloCall.Callback<GetTopRepositoriesQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.d(" Error:" , e.message.toString())
//                    completion(Pair(null, Error(e.message)))
                }
                override fun onResponse(response: Response<GetTopRepositoriesQuery.Data>) {
                    Log.d(" Repository; " , response.data?.user().toString())
                    var repoList = ArrayList<RepositoryDetail>()
                    response.data?.user()?.topRepositories()?.edges()?.forEach { edge ->
                        var repo = RepositoryDetail(edge?.node()?.name(), edge?.node()?.description(),
                            RepoOwner(edge?.node()?.owner()?.avatarUrl().toString(), edge?.node()?.owner()?.login()))
                        repoList.add(repo)
                    }
                    completion(repoList)
                }
            })
    }

    fun getStarredRepositories(completion: (result: List<RepositoryDetail>?) -> Unit){
        client.query(GetStarredRepositoriesQuery   //From the auto generated class
            .builder()
            .build())
            .enqueue(object : ApolloCall.Callback<GetStarredRepositoriesQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.d(" Error:" , e.message.toString())
//                    completion(Pair(null, Error(e.message)))
                }
                override fun onResponse(response: Response<GetStarredRepositoriesQuery.Data>) {
                    Log.d(" Repository; " , response.data?.user().toString())
                    var repoList = ArrayList<RepositoryDetail>()
                    response.data?.user()?.starredRepositories()?.edges()?.forEach { edge ->
                        var repo = RepositoryDetail(edge?.node()?.name(), edge?.node()?.description(),
                            RepoOwner(edge?.node()?.owner()?.avatarUrl().toString(), edge?.node()?.owner()?.login()))
                        repoList.add(repo)
                    }
                    completion(repoList)
                }
            })
    }
}