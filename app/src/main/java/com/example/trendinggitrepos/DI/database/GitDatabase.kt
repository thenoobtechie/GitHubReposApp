package com.app.nasatask.DI.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trendinggitrepos.DI.database.GitHubReposDao
import com.example.trendinggitrepos.model.RepoModel

@Database(entities = [RepoModel::class], version = 1,exportSchema = false)
abstract class GitDatabase : RoomDatabase() {

    abstract fun gitDao(): GitHubReposDao

    companion object {
        val DATABASE_NAME = "gitHubRepos.db"
    }


}
