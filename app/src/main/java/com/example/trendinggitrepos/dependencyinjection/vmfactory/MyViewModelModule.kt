package com.app.nasatask.DI.VMFactory

import androidx.lifecycle.ViewModel
import com.example.trendinggitrepos.viewmodel.GithubRepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MyViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GithubRepoViewModel::class)
    abstract fun bindMainActivityViewModel(myViewModel: GithubRepoViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ContributorViewModel::class)
//    abstract fun bindContributorsViewModel(myViewModel: ContributorViewModel): ViewModel
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(RepoViewModel::class)
//    abstract fun bindRepoViewModel(myViewModel: RepoViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LocalViewModel::class)
//    abstract fun bindLocalViewModel(myViewModel: LocalViewModel): ViewModel
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SearchViewModel::class)
//    abstract fun bindSearchViewModel(myViewModel: SearchViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(RepoDetailsViewModel::class)
//    abstract fun bindRepoDetailsModel(myViewModel: RepoDetailsViewModel): ViewModel
}