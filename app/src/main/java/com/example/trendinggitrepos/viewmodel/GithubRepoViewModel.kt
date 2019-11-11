package com.example.trendinggitrepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trendinggitrepos.dependencyinjection.network.GitHubApiService
import com.example.trendinggitrepos.dependencyinjection.database.GitHubReposDao
import com.example.trendinggitrepos.datasource.GitHubRepo
import com.example.trendinggitrepos.model.RepoModel
import javax.inject.Inject

class GithubRepoViewModel @Inject constructor(val dao: GitHubReposDao,val networkService: GitHubApiService): ViewModel() {

    @Inject
    internal lateinit var  gitHubRepo: GitHubRepo

    var liveData: LiveData<List<RepoModel>>? = null

    fun getLiveData(forceUpdate: Boolean): LiveData<List<RepoModel>>? {

        if (liveData == null || forceUpdate)
            liveData = gitHubRepo.getLiveRepos(forceUpdate)

        return liveData
    }

    fun getLiveRequestStatus(): LiveData<Boolean> = gitHubRepo.getLiveRequestStatus()
}