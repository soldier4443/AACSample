package com.turastory.aacsample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-13.
 */

class GitHubRepoViewModel : ViewModel() {
    lateinit var username: String
    lateinit var repos: LiveData<List<GitHubRepo>>
}