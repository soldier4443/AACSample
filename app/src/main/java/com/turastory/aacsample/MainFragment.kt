package com.turastory.aacsample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

    private lateinit var titleText: TextView
    private lateinit var repositoryListView: RecyclerView
    private lateinit var usernameEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var loadingScreen: ViewGroup

    private lateinit var gitHubRepoAdapter: GitHubRepoAdapter

    private val viewModel: GitHubRepoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.edit_text_username)
        loadingScreen = view.findViewById(R.id.loading_screen)

        setupTitle(view)
        setupAdapter(view)
        setupButton(view)
    }

    private fun setupTitle(view: View) {
        titleText = view.findViewById(R.id.title)
        viewModel.username.observe({ lifecycle }, { username ->
            if (!username.isNullOrEmpty())
                showTitleWithUsername(username)
            else
                showJustTitle()
        })
    }

    private fun showTitleWithUsername(username: String?) {
        titleText.text = getString(R.string.github_search_repo_title_with_name, username)
    }

    private fun showJustTitle() {
        titleText.text = getString(R.string.github_search_repo_title)
    }

    private fun setupAdapter(view: View) {
        gitHubRepoAdapter = GitHubRepoAdapter()

        repositoryListView = view.findViewById(R.id.repo_list)
        repositoryListView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = gitHubRepoAdapter
        }
    }

    private fun setupButton(view: View) {
        searchButton = view.findViewById(R.id.button_search)
        searchButton.setOnClickListener {
            gitHubRepoAdapter.repos = null
            showLoadingScreen()

            viewModel.init(usernameEditText.text.toString())
            viewModel.repos.observe(this, Observer { repos ->
                if (repos != null) {
                    gitHubRepoAdapter.repos = repos
                    hideLoadingScreen()
                }
            })
        }
    }

    private fun showLoadingScreen() {
        loadingScreen.visibility = View.VISIBLE
    }

    private fun hideLoadingScreen() {
        loadingScreen.visibility = View.INVISIBLE
    }
}