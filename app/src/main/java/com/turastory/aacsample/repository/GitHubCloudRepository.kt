package com.turastory.aacsample.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.turastory.aacsample.extensions.fromJson
import com.turastory.aacsample.vo.GitHubRepo

/**
 * Created by tura on 2018-07-16.
 */

class GitHubCloudRepository : GitHubRepository {
    init {
        FuelManager.instance.basePath = "https://api.github.com/"
    }

    override fun getRepos(username: String): LiveData<List<GitHubRepo>> =
        MutableLiveData<List<GitHubRepo>>().also {
            "$username/repos".httpGet().responseJson { _, response, result ->
                when (result) {
                    is Result.Failure -> {
                        // TODO: when status code is 404, should notify that the user does not exist. For now, just leave it like this
                        if (response.statusCode == 404)
                            it.value = listOf()
                        else
                            result.error.printStackTrace()
                    }
                    is Result.Success -> {
                        it.value = result.value.content.fromJson()
                    }
                }
            }
        }
}
