package com.levibostian.lifefeed.service

import retrofit2.http.GET
import com.levibostian.lifefeed.vo.RepoVo
import retrofit2.Call
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<RepoVo>>

}