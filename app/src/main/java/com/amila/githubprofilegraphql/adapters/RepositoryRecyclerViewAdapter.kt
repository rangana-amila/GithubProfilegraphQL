package com.amila.githubprofilegraphql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amila.githubprofilegraphql.R
import com.amila.githubprofilegraphql.databinding.ItemRepositoryBinding
import com.amila.githubprofilegraphql.models.RepositoryDetail
import com.squareup.picasso.Picasso

class RepositoryRecyclerViewAdapter(private var repoList:List<RepositoryDetail>):
    RecyclerView.Adapter<RepositoryRecyclerViewAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryRecyclerViewAdapter.RepositoryViewHolder {

        val itemRepositoryBinding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return RepositoryViewHolder(itemRepositoryBinding)
    }

    fun setData(data:List<RepositoryDetail>) {
        repoList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(viewHolder: RepositoryViewHolder, position: Int) {

        val repoItem = repoList[position]
        viewHolder.itemBinding.loginName.text = repoItem.repoOwner?.login
        Picasso.get().load(repoItem.repoOwner?.imageUrl).placeholder(R.drawable.ic_launcher_foreground)
            .into(viewHolder.itemBinding.imgAvatar)
        viewHolder.itemBinding.repoName.text = repoItem.repoName
        viewHolder.itemBinding.repoDescription.text = repoItem.description
    }

    inner class RepositoryViewHolder(val itemBinding:ItemRepositoryBinding): RecyclerView.ViewHolder(itemBinding.root)

}