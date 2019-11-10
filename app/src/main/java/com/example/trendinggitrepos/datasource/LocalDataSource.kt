package com.example.trendinggitrepos.datasource

import androidx.lifecycle.LiveData
import com.example.trendinggitrepos.DI.database.GitHubReposDao
import com.example.trendinggitrepos.model.RepoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LocalDataSource(val dao: GitHubReposDao) : CoroutineScope {


    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO


    fun saveRepos(repos: List<RepoModel>) {

        launch {
            dao.insertRepos(repos)
        }
    }

    fun getLiveRepos(): LiveData<List<RepoModel>>? = dao.getLiveRepos()
}