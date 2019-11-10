package com.example.trendinggitrepos.dependencyinjection.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.trendinggitrepos.model.RepoModel

@Dao
interface GitHubReposDao {

    @Query("SELECT * FROM gitHubRepos")
    fun getLiveRepos(): LiveData<List<RepoModel>>?

    @Insert(onConflict = REPLACE)
    fun insertRepos(repos: List<RepoModel>): LongArray

    @Query("DELETE FROM gitHubRepos")
    fun deleteRepos()
}