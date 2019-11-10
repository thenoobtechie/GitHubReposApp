package com.example.trendinggitrepos.datasource

import android.content.Context.MODE_PRIVATE
import com.example.trendinggitrepos.Constants.Companion.LAST_FETCH_TIME
import com.example.trendinggitrepos.MyApplication
import com.example.trendinggitrepos.dependencyinjection.network.GitHubApiService
import com.example.trendinggitrepos.model.RepoModel
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.*
import kotlin.coroutines.CoroutineContext

class RemoteDataSource(
    private val networkService: GitHubApiService
) : CoroutineScope {

    lateinit var onDataUpdateCallback: DataUpdateCallback

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun fetchTrendingRepos() {

        launch {

            var response: Response<List<RepoModel>>? = null
            val call = networkService.getListOfRepos()
            withContext(Dispatchers.IO) {
                response = call.awaitResponse()
            }

            response?.let {

                if (it.isSuccessful)
                    it.body()?.let {

                        val sp = MyApplication.application.getSharedPreferences(
                            LAST_FETCH_TIME,
                            MODE_PRIVATE
                        )
                        sp.edit().putLong(LAST_FETCH_TIME, Date().time).apply()
                        onDataUpdateCallback.onDataUpdated(repos = it)
                    }
                        ?: onDataUpdateCallback.onDataFetchFailure(it.message())
                else
                    onDataUpdateCallback.onDataFetchFailure(it.message())

            } ?: onDataUpdateCallback.onDataFetchFailure(null)
        }
    }
}

interface DataUpdateCallback {
    fun onDataUpdated(repos: List<RepoModel>)
    fun onDataFetchFailure(errorMsg: String?)
}