package com.example.trendinggitrepos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.trendinggitrepos.adapter.RepoListAdapter
import com.example.trendinggitrepos.model.RepoModel
import com.example.trendinggitrepos.viewmodel.GithubRepoViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var repoListAdapter: RepoListAdapter

    @Inject
    internal lateinit var viewModel: GithubRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
            TODO("ADD PULL TO REFRESH")
            TODO("Set empty screen with retry button")
            TODO("CACHE EXPIRY TIMEOUT")
            TODO("DEPENDENCY INJECTION")
            TODO("UNIT/UI TESTS")
        */

        viewModel.liveData?.observe(this, Observer {
            it?.let {
                val jsonStr = Gson().toJson(it)

                val listType = object : TypeToken<List<RepoModel>>() {

                }.type

                val repos: List<RepoModel> = Gson().fromJson(jsonStr, listType)
                if (repos.isNotEmpty()) {
                    repoListAdapter.setData(repos = repos)
                }
            }
        })

        repoListAdapter = RepoListAdapter(rv_repo_list)

        rv_repo_list.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        rv_repo_list.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        rv_repo_list.adapter = repoListAdapter
    }
}
