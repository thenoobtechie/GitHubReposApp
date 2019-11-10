package com.example.trendinggitrepos.datasource

import com.example.trendinggitrepos.Constants
import com.example.trendinggitrepos.DI.Network.GitHubApiService
import com.example.trendinggitrepos.model.RepoModel
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class RemoteDataSource(
    networkService: GitHubApiService
): CoroutineScope {
    lateinit var onDataUpdateCallback: DataUpdateCallback

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main




    val call = networkService.getListOfRepos()

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