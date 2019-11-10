package com.example.trendinggitrepos.datasource

import androidx.lifecycle.LiveData
import com.example.trendinggitrepos.DI.Network.GitHubApiService
import com.example.trendinggitrepos.DI.database.GitHubReposDao
import com.example.trendinggitrepos.model.RepoModel
import javax.inject.Inject

class GitHubRepo @Inject constructor(var localDataSource : LocalDataSource,var remoteDataSource :RemoteDataSource)
    : DataUpdateCallback {


    init {
        remoteDataSource.onDataUpdateCallback=this
    }

    fun getLiveRepos(): LiveData<List<RepoModel>>? {
        remoteDataSource.fetchTrendingRepos()
        return localDataSource.getLiveRepos()
    }

    override fun onDataUpdated(repos: List<RepoModel>) {
        localDataSource.saveRepos(repos)
    }


}