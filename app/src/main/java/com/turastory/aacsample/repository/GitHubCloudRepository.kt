package com.turastory.aacsample.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.turastory.aacsample.extensions.fromJson
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-16.
 */

class GitHubCloudRepository : GitHubRepository {
    override fun getRepo(username: String): LiveData<List<GitHubRepo>> {
        val repos = MutableLiveData<List<GitHubRepo>>()

        "https://api.github.com/users/$username/repos".httpGet().responseString { _, _, result ->
            when (result) {
                is Result.Failure -> {

                }
                is Result.Success -> {
                    repos.value = result.get().fromJson()
                }
            }
        }

        return repos
    }
}