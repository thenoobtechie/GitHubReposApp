package com.example.trendinggitrepos.datasource

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.trendinggitrepos.Constants
import com.example.trendinggitrepos.dependencyinjection.database.GitHubReposDao
import com.example.trendinggitrepos.MyApplication
import com.example.trendinggitrepos.model.RepoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class LocalDataSource(private val dao: GitHubReposDao) : CoroutineScope {

    //Cache expiry time set to 2 hours
    private val cacheExpiryTime: Long = 2 * 60 * 60 * 1000

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

    fun getLiveRepos(): LiveData<List<RepoModel>>? = dao.getLiveRepos()

    fun saveRepos(repos: List<RepoModel>) {

        launch {
            dao.insertRepos(repos)
        }
    }

    //Delete data if cache is expired
    fun deleteExistingData() {
        launch {
            dao.deleteRepos()
        }
    }

    internal fun isCacheExpired(): Boolean {
        val sp = MyApplication.application.getSharedPreferences(
            Constants.LAST_FETCH_TIME,
            Context.MODE_PRIVATE
        )
        val lastFetchTime = sp.getLong(Constants.LAST_FETCH_TIME, -1L)

        return lastFetchTime == -1L || lastFetchTime + cacheExpiryTime < Date().time
    }
}