package com.turastory.aacsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.ViewModelStore
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

/**
 * Created by tura on 2018-07-13.
 *
 * NOTE: callback functions order
 *       onCreateView()
 *       onViewCreated()
 *       onActivityCreated()
 */

class MainFragment : Fragment() {

    private lateinit var repositoryListView: RecyclerView
    private lateinit var usernameEditText: EditText
    private lateinit var searchButton: Button

    private lateinit var adapter: GitHubRepoAdapter

    private lateinit var viewModel: GitHubRepoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoryListView = view.findViewById(R.id.repo_list)
        usernameEditText = view.findViewById(R.id.edit_text_username)
        searchButton = view.findViewById(R.id.button_search)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(GitHubRepoViewModel::class.java)
        viewModel.repos.observe(this, Observer { repos ->
            if (repos != null)
                adapter.repos = repos
        })
    }
}