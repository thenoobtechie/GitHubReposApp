package com.example.trendinggitrepos.datasource

import com.example.trendinggitrepos.Constants
import com.example.trendinggitrepos.model.RepoModel
import com.example.trendinggitrepos.network.GitHubApiService
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class RemoteDataSource(var onDataUpdateCallback: DataUpdateCallback): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main


    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    private val apiService = retrofit.create(GitHubApiService::class.java)
    val call = apiService.getListOfRepos()

    fun fetchTrendingRepos() {
        launch {
            var response: Response<List<RepoModel>>? = null
            withContext(Dispatchers.IO) {
                response = call.awaitResponse()
            }

            response?.body()?.let { onDataUpdateCallback.onDataUpdated(repos = it) }
        }
    }
}

interface DataUpdateCallback {
    fun onDataUpdated(repos: List<RepoModel>)
}