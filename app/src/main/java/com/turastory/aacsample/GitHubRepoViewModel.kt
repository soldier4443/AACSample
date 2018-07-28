package com.turastory.aacsample

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.turastory.aacsample.persistence.cache.InMemoryCache
import com.turastory.aacsample.repository.GitHubRepository
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-13.
 */

class GitHubRepoViewModel(private val repository: GitHubRepository) : ViewModel() {
    // I used MediatorLiveData for the following reasons.
    // 1. We can expose stable instance of LiveData, which is immutable.
    // 2. Calling order of methods doesn't matter anymore.
    //    (We can observe this LiveData before we load repos. It was not possible in the previous version..)
    val repos = MediatorLiveData<List<GitHubRepo>>()

    private val reposCache = InMemoryCache<List<GitHubRepo>>()

    fun loadRepos(username: String) {
        reposCache[username]?.let {
            repos.value = it
        } ?: let {
            val newRepos = repository.getRepos(username)
            repos.addSource(newRepos) {
                it?.let {
                    repos.removeSource(newRepos)
                    repos.value = it
                    reposCache[username] = it
                }
            }
        }
    }
}