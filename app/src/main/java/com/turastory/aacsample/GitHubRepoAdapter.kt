package com.turastory.aacsample

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.turastory.aacsample.extensions.inflate
import com.turastory.aacsample.vo.GitHubRepo


/**
 * Created by tura on 2018-07-16.
 */

class GitHubRepoAdapter : RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {

    var username: String? = null
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
        repos?.get(position)?.let { holder.bind(username, it) }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_github_repo)) {
        private val name: TextView = itemView.findViewById(R.id.repo_name)
        private val description: TextView = itemView.findViewById(R.id.repo_description)
        private val openButton: ImageView = itemView.findViewById(R.id.repo_button)

        fun bind(username: String?, item: GitHubRepo) {
            name.text = item.name
            description.text = item.description
            openButton.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(constructRepositoryPath(item.name, username)))
                itemView.context?.startActivity(browserIntent)
            }
        }

        private fun constructRepositoryPath(repositoryName: String, username: String?): String =
            "https://github.com/$username/$repositoryName"
    }
}