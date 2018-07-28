package com.turastory.aacsample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.turastory.aacsample.vo.GitHubRepo
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by tura on 2018-07-13.
 *
 * NOTE: callback functions order
 *       onCreateView()
 *       onViewCreated()
 *       onActivityCreated()
 */

class RepoListFragment : Fragment() {

    companion object {
        const val TAG = "RepoListFragment"
    }

    private lateinit var gitHubRepoAdapter: GitHubRepoAdapter

    private val viewModel: GitHubRepoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupButton()

        showJustTitle()
    }

    private fun setupAdapter() {
        gitHubRepoAdapter = GitHubRepoAdapter()

        repo_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = gitHubRepoAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.repos.observe(this, Observer(this@RepoListFragment::showRepos))
    }

    private fun showRepos(repos: List<GitHubRepo>?) {
        if (repos != null) {
            gitHubRepoAdapter.repos = repos
            hideLoadingScreen()
        } else {
            showLoadingScreen()
        }
    }

    private fun setupButton() {
        button_search.setOnClickListener {
            edit_text_username.text.toString().let {
                showUsername(it)
                showLoadingScreen()
                viewModel.loadRepos(it)
            }
        }
    }

    private fun showUsername(username: String) {
        if (!username.isEmpty())
            showTitleWithUsername(username)
        else
            showJustTitle()
    }

    private fun showTitleWithUsername(username: String?) {
        title.text = getString(R.string.github_search_repo_title_with_name, username)
    }

    private fun showJustTitle() {
        title.text = getString(R.string.github_search_repo_title)
    }

    private fun showLoadingScreen() {
        loading_screen.visibility = View.VISIBLE
    }

    private fun hideLoadingScreen() {
        loading_screen.visibility = View.INVISIBLE
    }
}