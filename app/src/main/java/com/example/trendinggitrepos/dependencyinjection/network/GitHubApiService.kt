package com.example.trendinggitrepos.dependencyinjection.network

import com.example.trendinggitrepos.Constants.Companion.TRENDING_REPOS_URL
import com.example.trendinggitrepos.model.RepoModel
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApiService {

    @GET(TRENDING_REPOS_URL)
    fun getListOfRepos(): Call<List<RepoModel>>
}
