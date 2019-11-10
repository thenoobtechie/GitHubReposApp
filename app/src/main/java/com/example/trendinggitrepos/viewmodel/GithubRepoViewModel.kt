package com.example.trendinggitrepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trendinggitrepos.DI.Network.GitHubApiService
import com.example.trendinggitrepos.DI.database.GitHubReposDao
import com.example.trendinggitrepos.datasource.GitHubRepo
import com.example.trendinggitrepos.model.RepoModel
import javax.inject.Inject

class GithubRepoViewModel @Inject constructor(val dao: GitHubReposDao,val networkService: GitHubApiService): ViewModel() {


    @Inject
    internal lateinit var  gitHubRepo: GitHubRepo
    //= GitHubRepo.getInstance(dao,networkService)



    var liveData: LiveData<List<RepoModel>>? = null
        get() {
            field ?: let { field = gitHubRepo.getLiveRepos() }
            return field
        }
}