package com.zeynelerdi.mvpgithub.ui.main

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeynelerdi.mvpgithub.R
import com.zeynelerdi.mvpgithub.api.models.GithubRepo
import com.zeynelerdi.mvpgithub.constants.ArgConstants
import com.zeynelerdi.mvpgithub.room.GithubRepoEntity
import com.zeynelerdi.mvpgithub.room.GithubRepoViewModel
import com.zeynelerdi.mvpgithub.ui.base.BaseActivity
import com.zeynelerdi.mvpgithub.ui.main.adapter.RepoAdapter
import com.zeynelerdi.mvpgithub.ui.repoDetail.RepoDetailActivity
import com.zeynelerdi.mvpgithub.utils.KeyboardUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), MainContract.View, RepoAdapter.OnClickListener {

    private var githubRepoViewModel: GithubRepoViewModel? = null
    private var mPresenter: MainContract.Presenter = MainPresenter(this)
    private lateinit var repoAdapter: RepoAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun bindView() {
        rvRepo.layoutManager = LinearLayoutManager(baseActivity, RecyclerView.VERTICAL, false)
        githubRepoViewModel = ViewModelProviders.of(this).get(GithubRepoViewModel::class.java)
        ivLoading.imageAssetsFolder = "/"
        ivLoading.setAnimation("basic_animation.json")
        ivLoading.repeatCount = ValueAnimator.INFINITE

        btnSend.setOnClickListener {
            if (etInput.text.toString().isNotEmpty()) {
                KeyboardUtils.hideKeyboard(this)
                mPresenter.getRepoList(etInput.text.toString())
            } else {
                showToast(getString(R.string.username_not_valid))
            }
        }
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun getRepoListSuccess(repoList: List<GithubRepo>) {
        githubRepoViewModel!!.getAllRepos().observe(this, Observer<List<GithubRepoEntity>> {
            if (it.isNotEmpty()) {
                for (item in it) {
                    for (repo in repoList) {
                        if (item.repoID == repo.repoID) {
                            repo.isFavourited = item.isFavourited
                        }
                    }
                }
            }
            repoAdapter = RepoAdapter(repoList, this)
            rvRepo.adapter = repoAdapter
            showLoading(false)
        })
    }

    override fun getRepoListFail(error: String) {
        if (error.isNotEmpty()) {
            showToast(error)
        } else {
            showToast(getString(R.string.general_error))
        }
    }

    override fun onRepoClicked(githubRepo: GithubRepo) {
        val bundle = Bundle()
        bundle.putParcelable(ArgConstants.REPO, githubRepo)
        val intent = Intent(baseActivity, RepoDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showLoading(isStarted: Boolean) {
        if (isStarted) {
            ivLoading.visibility=VISIBLE
            ivLoading.playAnimation()
        } else {
            ivLoading.cancelAnimation()
            ivLoading.visibility= GONE
        }
        btnSend.isClickable = !isStarted
    }

    private fun showToast(error: String) {
        Toast.makeText(baseActivity, error, Toast.LENGTH_SHORT).show()
    }
}
