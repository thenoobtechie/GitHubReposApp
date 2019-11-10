package com.example.trendinggitrepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trendinggitrepos.datasource.GitHubRepo
import com.example.trendinggitrepos.model.RepoModel

class GithubRepoViewModel : ViewModel() {

    private val gitHubRepoDataSource: GitHubRepo = GitHubRepo.getInstance()

    var liveData: LiveData<List<RepoModel>>? = null
        get() {
            field ?: let { field = gitHubRepoDataSource.getLiveRepos() }
            return field
        }
}