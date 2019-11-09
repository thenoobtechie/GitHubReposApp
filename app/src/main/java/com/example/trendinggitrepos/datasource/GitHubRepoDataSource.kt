package com.example.trendinggitrepos.datasource

import androidx.lifecycle.LiveData
import com.example.trendinggitrepos.database.AppDB
import com.example.trendinggitrepos.model.RepoModel

class GitHubRepoDataSource : DataUpdateCallback {

    val localDataSource = LocalDataSource()
    val remoteDataSource = RemoteDataSource(this)

    fun getLiveRepos(): LiveData<List<RepoModel>>? {
        remoteDataSource.fetchTrendingRepos()
        return AppDB.instance!!.gitHubReposDao().getLiveRepos()
    }

    override fun onDataUpdated(repos: List<RepoModel>) {
        localDataSource.saveRepos(repos)
    }

    companion object
    {
        fun getInstance(): GitHubRepoDataSource {
            return GitHubRepoDataSource()
        }
    }
}