package com.turastory.aacsample.repository

import android.arch.lifecycle.LiveData
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-16.
 */

interface GitHubRepository {
    fun getRepos(username: String): LiveData<List<GitHubRepo>>
}