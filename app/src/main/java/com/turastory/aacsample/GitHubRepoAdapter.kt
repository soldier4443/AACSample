package com.turastory.aacsample

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.turastory.aacsample.extensions.inflate
import com.turastory.aacsample.vo.GitHubRepo
import kotlinx.android.synthetic.main.item_github_repo.view.*

/**
 * Created by tura on 2018-07-16.
 */

class GitHubRepoAdapter : RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {

    var repos: List<GitHubRepo>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return repos?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        repos?.get(position)?.let { holder.bind(it) }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_github_repo)) {
        fun bind(item: GitHubRepo) {
            itemView.repo_name.text = item.name
            itemView.repo_description.text = item.description
            itemView.repo_button.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                itemView.context?.startActivity(browserIntent)
            }
        }
    }
}