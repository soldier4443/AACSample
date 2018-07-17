package com.turastory.aacsample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.turastory.aacsample.repository.GitHubRepository
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-13.
 */

class GitHubRepoViewModel(private val repository: GitHubRepository) : ViewModel() {
    val username = MutableLiveData<String>()
    lateinit var repos: LiveData<List<GitHubRepo>>

    fun init(username: String) {
        this.username.value = username
        this.repos = repository.getRepos(username)
    }
}