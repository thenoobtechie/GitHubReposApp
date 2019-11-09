package com.example.trendinggitrepos.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trendinggitrepos.MyApplication
import com.example.trendinggitrepos.database.daos.GitHubReposDao
import com.example.trendinggitrepos.model.RepoModel

@Database(
    entities = [
        RepoModel::class
    ],
    version = 1
)
abstract class AppDB : RoomDatabase() {

    abstract fun gitHubReposDao(): GitHubReposDao

    companion object {

        var instance: AppDB? = null
            get() {
                if (field == null) {
                    field = Room.databaseBuilder(
                        MyApplication.application.applicationContext,
                        AppDB::class.java,
                        "gitHubRepos.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return field
            }
    }
}