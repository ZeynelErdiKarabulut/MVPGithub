package com.zeynelerdi.mvpgithub.ui.repoDetail

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.zeynelerdi.mvpgithub.R
import com.zeynelerdi.mvpgithub.api.models.GithubRepo
import com.zeynelerdi.mvpgithub.constants.ArgConstants
import com.zeynelerdi.mvpgithub.room.GithubRepoEntity
import com.zeynelerdi.mvpgithub.room.GithubRepoViewModel
import com.zeynelerdi.mvpgithub.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.tvRepo
import kotlinx.android.synthetic.main.activity_repo_detail.*


class RepoDetailActivity : BaseActivity(), RepoDetailContract.View {

    private var githubRepoViewModel: GithubRepoViewModel? = null
    private var repo: GithubRepo? = null
    private var mPresenter: RepoDetailContract.Presenter = RepoDetailPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.activity_repo_detail
    }

    override fun bindView() {
        repo = intent?.extras?.getParcelable(ArgConstants.REPO)
        githubRepoViewModel = ViewModelProviders.of(this).get(GithubRepoViewModel::class.java)

        tvRepo.text = repo?.repoName
        tvOpenIssues.text = String.format(getString(R.string.open_issues_prefix), repo?.openIssuesCount)
        tvStars.text = String.format(getString(R.string.stars_prefix), repo?.starCount)
        tvOwner.text = repo?.repoOwner?.ownerName

        if (!repo?.repoOwner?.avatarUrl.isNullOrBlank()) {
            Picasso.get().load(repo!!.repoOwner.avatarUrl).into(ivPhoto)
        }

        checkStar()

        ivStar.setOnClickListener {
            githubRepoViewModel!!.insert(GithubRepoEntity(repo?.repoID!!, !repo!!.isFavourited))
            repo?.isFavourited = !ivStar.isSelected
            checkStar()
        }
        ivBack.setOnClickListener { onBackPressed() }
    }

    private fun checkStar() {
        if (repo?.isFavourited!!) {
            ivStar.setImageDrawable(ContextCompat.getDrawable(baseActivity, R.drawable.ic_star))
            ivStar.isSelected = true
        } else {
            ivStar.setImageDrawable(ContextCompat.getDrawable(baseActivity, R.drawable.ic_star_border))
            ivStar.isSelected = false
        }
    }

    override fun setPresenter(presenter: RepoDetailContract.Presenter) {
        mPresenter = presenter
    }
}
