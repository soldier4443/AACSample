package com.turastory.aacsample.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.turastory.aacsample.extensions.fromJson
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-16.
 */

class GitHubCloudRepository : GitHubRepository {
    override fun getRepos(username: String): LiveData<List<GitHubRepo>> {
        val repos = MutableLiveData<List<GitHubRepo>>()

        "https://api.github.com/users/$username/repos".httpGet().responseJson { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    // TODO: when status code is 404, should notify that the user does not exist. For now, just leave it like this
                    if (response.statusCode == 404)
                        repos.value = listOf()
                    else
                        result.error.printStackTrace()
                }
                is Result.Success -> {
                    repos.value = result.value.content.fromJson()
                }
            }
        }

        return repos
    }
}
