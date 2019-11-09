package com.example.trendinggitrepos.network

import com.example.trendinggitrepos.model.RepoModel
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApiService {

    @GET("repositories?language=&since=daily")
    fun getListOfRepos(): Call<List<RepoModel>>
}
