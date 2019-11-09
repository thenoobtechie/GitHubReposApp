package com.example.trendinggitrepos.datasource

import com.example.trendinggitrepos.database.AppDB
import com.example.trendinggitrepos.model.RepoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LocalDataSource: CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

    private val dataDao = AppDB.instance?.gitHubReposDao()

    fun saveRepos(repos: List<RepoModel>) {

        launch {
            dataDao?.insertRepos(repos)
        }
    }
}