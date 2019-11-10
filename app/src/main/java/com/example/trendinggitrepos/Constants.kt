package com.example.trendinggitrepos

class Constants {

    companion object {

        //URLS
        const val BASE_URL = "https://github-trending-api.now.sh/"
        const val TRENDING_REPOS_URL = "repositories?language=&since=daily"
        const val LAST_FETCH_TIME = "lastFetchTime"

        //Table names
        const val GIT_HUB_REPO_TABLE = "gitHubRepos"
    }
}