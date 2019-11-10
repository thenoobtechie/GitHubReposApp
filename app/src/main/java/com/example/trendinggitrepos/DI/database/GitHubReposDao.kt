package com.example.trendinggitrepos.DI.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.trendinggitrepos.model.RepoModel

@Dao
interface GitHubReposDao {

    @Query("Select * from gitHubRepos")
    fun getLiveRepos(): LiveData<List<RepoModel>>?

    @Insert(onConflict = REPLACE)
    fun insertRepos(repos: List<RepoModel>): LongArray
}