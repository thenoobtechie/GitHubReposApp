package com.example.trendinggitrepos.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trendinggitrepos.MyApplication
import com.example.trendinggitrepos.Utility
import com.example.trendinggitrepos.model.RepoModel
import javax.inject.Inject

class GitHubRepo @Inject constructor(
    var localDataSource: LocalDataSource,
    var remoteDataSource: RemoteDataSource
) : DataUpdateCallback {

    var isCacheExpired = false

    private val isDataFetchSuccessful = MutableLiveData<Boolean>()

    init {
        remoteDataSource.onDataUpdateCallback = this
    }

    fun getLiveRepos(forceUpdate: Boolean): LiveData<List<RepoModel>>? {

        isCacheExpired = localDataSource.isCacheExpired()

        if (isCacheExpired || forceUpdate) {

            Utility.showToast(MyApplication.application, "fetching data")
            localDataSource.deleteExistingData()
            remoteDataSource.fetchTrendingRepos()
        }

        return localDataSource.getLiveRepos()
    }

    fun getLiveResponse(): MutableLiveData<Boolean> = isDataFetchSuccessful

    override fun onDataUpdated(repos: List<RepoModel>) {

        isCacheExpired = false
        isDataFetchSuccessful.postValue(true)
        localDataSource.saveRepos(repos)
    }

    override fun onDataFetchFailure(errorMsg: String?) {
        isDataFetchSuccessful.postValue(false)
    }


}