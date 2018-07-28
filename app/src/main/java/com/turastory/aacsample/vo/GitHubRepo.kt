package com.turastory.aacsample.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by tura on 2018-07-16.
 *
 * Describes repository in github.
 */

data class GitHubRepo(val id: Int,
                      val name: String,
                      @SerializedName("html_url") var url: String,
                      val description: String)