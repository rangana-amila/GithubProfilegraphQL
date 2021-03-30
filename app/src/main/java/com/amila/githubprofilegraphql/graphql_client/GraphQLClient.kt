package com.amila.githubprofilegraphql.graphql_client

import com.apollographql.apollo.ApolloClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

object GraphQLClient {
    private val GITHUB_GRAPHQL_ENDPOINT = "https://api.github.com/graphql"

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(NetworkInterceptor())
            .build()
    }


    val apolloClient: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl(GITHUB_GRAPHQL_ENDPOINT)
            .okHttpClient(httpClient)
            .build()
    }

    private class NetworkInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain?): Response {
            return chain!!.proceed(chain.request().newBuilder().header("Authorization", "Bearer dd9170f3b82bb9e8fa4e05e3180060d7599aa377").build())
        }
    }

}