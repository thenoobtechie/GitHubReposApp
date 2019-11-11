package com.example.trendinggitrepos

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.trendinggitrepos.adapter.RepoListAdapter
import com.example.trendinggitrepos.model.RepoModel
import com.example.trendinggitrepos.viewmodel.GithubRepoViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_screen.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var repoListAdapter: RepoListAdapter

    @Inject
    internal lateinit var viewModel: GithubRepoViewModel

    private var liveData: LiveData<List<RepoModel>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
            TODO("UNIT/UI TESTS")
        */

        liveData = viewModel.getLiveData(false)

        observeData()

        viewModel.getLiveRequestStatus().observe(this, Observer {
                isSuccess ->

            swipe_refresh_layout.isRefreshing = false
            if(isSuccess) displayEmptyScreen(false) else displayEmptyScreen(true)
        })

        swipe_refresh_layout.setOnRefreshListener { viewModel.getLiveData(true) }
        btn_retry.setOnClickListener { viewModel.getLiveData(true) }

        rv_repo_list.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        rv_repo_list.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        rv_repo_list.adapter = repoListAdapter
    }

    private fun observeData() {

        liveData?.removeObservers(this)
        liveData?.observe(this, Observer {
            it?.let {
                val jsonStr = Gson().toJson(it)

                val listType = object : TypeToken<List<RepoModel>>() {

                }.type

                val repos: List<RepoModel> = Gson().fromJson(jsonStr, listType)
                if (repos.isNotEmpty()) {
                    repoListAdapter.setData(repos = repos)
                }
                else repoListAdapter.setData(null)
            }
        })
    }

    private fun displayEmptyScreen(show: Boolean) {
        empty_screen.visibility = if (show) VISIBLE else GONE
    }
}
