package com.app.nasatask.DI.VMFactory

import androidx.lifecycle.ViewModel
import com.example.trendinggitrepos.DI.Network.GitHubApiService
import com.example.trendinggitrepos.DI.database.GitHubReposDao
import com.example.trendinggitrepos.datasource.GitHubRepo
import com.example.trendinggitrepos.datasource.LocalDataSource
import com.example.trendinggitrepos.datasource.RemoteDataSource
import com.example.trendinggitrepos.viewmodel.GithubRepoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
public class MyRepoModule {

    @Provides
    fun getLocalRepo(dao: GitHubReposDao): LocalDataSource{
        return LocalDataSource(dao)
    }
    @Provides
    fun getRemoteRepo( networkService: GitHubApiService): RemoteDataSource{
        return RemoteDataSource(networkService)
    }

    @Provides
     fun getGitRepo(localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource): GitHubRepo{
        return GitHubRepo(localDataSource,remoteDataSource)
    }
}