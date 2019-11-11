package com.example.trendinggitrepos

import com.example.trendinggitrepos.adapter.RepoListAdapter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideRepoRecyclerAdapter(): RepoListAdapter {
        return RepoListAdapter()
    }

}