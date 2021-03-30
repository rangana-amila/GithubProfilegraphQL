package com.amila.githubprofilegraphql.utils


import androidx.databinding.BindingAdapter
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.amila.githubprofilegraphql.R
import com.squareup.picasso.Picasso

object AvatarImageViewBindingUtil {

    @JvmStatic
    @BindingAdapter("app:imageUri")
    fun setImage(view: AvatarImageView, url: String?) {
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(view)
    }
}