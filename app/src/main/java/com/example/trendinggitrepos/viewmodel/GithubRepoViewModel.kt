package com.example.trendinggitrepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trendinggitrepos.Constants
import com.example.trendinggitrepos.Utility
import com.example.trendinggitrepos.datasource.GitHubRepoDataSource
import com.example.trendinggitrepos.model.RepoModel
import com.example.trendinggitrepos.network.GitHubApiService
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class GithubRepoViewModel : ViewModel() {

    private val gitHubRepoDataSource: GitHubRepoDataSource = GitHubRepoDataSource.getInstance()

    var liveData: LiveData<List<RepoModel>>? = null
        get() {
            field ?: let { field = gitHubRepoDataSource.getLiveRepos() }
            return field
        }
}