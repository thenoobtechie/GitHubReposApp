package com.example.trendinggitrepos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.trendinggitrepos.Constants
import org.jetbrains.annotations.PropertyKey

@Entity(
    tableName = Constants.GIT_HUB_REPO_TABLE
)
data class RepoModel(@ColumnInfo(name = "name") var name: String,
                     @ColumnInfo(name = "description") var description: String,
                     @ColumnInfo(name = "author") var author: String,
                     @ColumnInfo(name = "avatar") var avatar: String,
                     @PrimaryKey @ColumnInfo(name = "url") var url: String,
                     @ColumnInfo(name = "language") var language: String? = null,
                     @ColumnInfo(name = "stars") var stars: Int,
                     @ColumnInfo(name = "forks") var forks: Int)