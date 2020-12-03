package com.zeynelerdi.mvpgithub.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeynelerdi.mvpgithub.R
import com.zeynelerdi.mvpgithub.api.models.GithubRepo
import kotlinx.android.synthetic.main.listitem_repo.view.*

class RepoAdapter(private var repoList: List<GithubRepo>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as RepoViewHolder

        holder1.tvRepo.text = repoList[position].repoName

        if (repoList[position].isFavourited) {
            holder1.ivStar.visibility = View.VISIBLE
        } else {
            holder1.ivStar.visibility = View.GONE
        }

        if (repoList.size - 1 != position) {
            holder1.divider.visibility = View.VISIBLE
        } else {
            holder1.divider.visibility = View.GONE
        }

        holder1.rlRoot.setOnClickListener { onClickListener.onRepoClicked(repoList[position]) }
    }

    open class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRepo: TextView = view.tvRepo
        val divider: View = view.divider
        val ivStar: ImageView = view.ivStar
        val rlRoot: RelativeLayout = view.rlRoot
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return RepoViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    interface OnClickListener {
        fun onRepoClicked(githubRepo: GithubRepo)
    }
}
