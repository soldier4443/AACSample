package com.turastory.aacsample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by tura on 2018-07-13.
 *
 * NOTE: callback functions order
 *       onCreateView()
 *       onViewCreated()
 *       onActivityCreated()
 */

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private lateinit var gitHubRepoAdapter: GitHubRepoAdapter

    private val viewModel: GitHubRepoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTitle()
        setupAdapter()
        setupButton()
    }

    private fun setupTitle() {
        viewModel.username.observe({ lifecycle }, { username ->
            if (!username.isNullOrEmpty())
                showTitleWithUsername(username)
            else
                showJustTitle()
        })
    }

    private fun showTitleWithUsername(username: String?) {
        title.text = getString(R.string.github_search_repo_title_with_name, username)
    }

    private fun showJustTitle() {
        title.text = getString(R.string.github_search_repo_title)
    }

    private fun setupAdapter() {
        gitHubRepoAdapter = GitHubRepoAdapter()

        repo_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = gitHubRepoAdapter
        }
    }

    private fun setupButton() {
        button_search.setOnClickListener {
            gitHubRepoAdapter.repos = null
            showLoadingScreen()

            val username = edit_text_username.text.toString()
            gitHubRepoAdapter.username = username
            viewModel.init(username)
            viewModel.repos.observe(this, Observer { repos ->
                if (repos != null) {
                    gitHubRepoAdapter.repos = repos
                    hideLoadingScreen()
                }
            })
        }
    }

    private fun showLoadingScreen() {
        loading_screen.visibility = View.VISIBLE
    }

    private fun hideLoadingScreen() {
        loading_screen.visibility = View.INVISIBLE
    }
}