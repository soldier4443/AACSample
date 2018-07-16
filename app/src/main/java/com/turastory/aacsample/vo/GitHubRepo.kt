package com.turastory.aacsample.vo

/**
 * Created by tura on 2018-07-16.
 *
 * Describes repository in github.
 */

data class GitHubRepo(val id: Int,
                      val name: String,
                      var url: String,
                      val description: String)