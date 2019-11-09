package com.example.trendinggitrepos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendinggitrepos.MyApplication
import com.example.trendinggitrepos.R
import com.example.trendinggitrepos.Utility
import com.example.trendinggitrepos.model.RepoModel
import kotlinx.android.synthetic.main.repo_item_layout.view.*

class RepoListAdapter: RecyclerView.Adapter<RepoListAdapter.RepoItemViewHolder>() {

    // TODO("COLLAPSED/EXPANDED VIEW")

    private var repos: List<RepoModel>? = null
    private val DEFAULT_LOADING_SIZE = 15
    private val LOADING_VIEW_ = 0
    private val LOADED_VIEW_ = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val view: View
        if (viewType == LOADED_VIEW_)
            view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_layout, parent, false)
        else
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading_layout, parent, false)

        return RepoItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return repos?.let { LOADED_VIEW_ } ?: LOADING_VIEW_
    }

    override fun getItemCount(): Int = repos?.size ?: DEFAULT_LOADING_SIZE

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        repos?.let { holder.update(it[position]) }
    }

    fun setData(repos: List<RepoModel>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    inner class RepoItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun update(repoModel: RepoModel) {
            itemView.author.text = repoModel.author
            itemView.name.text = repoModel.name
            Utility.setImageFromUrl(MyApplication.application, repoModel.avatar, itemView.avatar)
        }
    }
}