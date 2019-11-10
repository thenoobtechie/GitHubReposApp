package com.app.nasatask.DI.VMFactory

import com.example.trendinggitrepos.dependencyinjection.network.GitHubApiService
import com.example.trendinggitrepos.dependencyinjection.database.GitHubReposDao
import com.example.trendinggitrepos.datasource.GitHubRepo
import com.example.trendinggitrepos.datasource.LocalDataSource
import com.example.trendinggitrepos.datasource.RemoteDataSource
import dagger.Module
import dagger.Provides

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