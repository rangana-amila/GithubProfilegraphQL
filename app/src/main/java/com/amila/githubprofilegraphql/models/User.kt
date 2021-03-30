package com.amila.githubprofilegraphql.models

data class User(var name:String?, var login: String?, var email: String?, var imageUrl: String?,
                var followers: String?, var followings: String?)