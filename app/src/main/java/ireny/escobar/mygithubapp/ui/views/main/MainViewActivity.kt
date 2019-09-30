package ireny.escobar.mygithubapp.ui.views.main

import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ireny.escobar.mygithubapp.R
import ireny.escobar.mygithubapp.adapters.IssuesAdapter
import ireny.escobar.mygithubapp.di.modules.MainModule
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.ui.views.base.BaseViewActivity
import ireny.escobar.mygithubapp.ui.views.details.DetailsViewActivity
import ireny.escobar.mygithubapp.ui.presenters.MainPresenter
import ireny.escobar.mygithubapp.utils.customApplication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainViewActivity : BaseViewActivity(), MainViewInterface{

    private val presenter: MainPresenter by lazy { component.presenter() }
    private val component by lazy { customApplication.component.plus(MainModule()) }

    lateinit var adapter: IssuesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupdapter()
        setupRecyclerView()
        setupswipeContainer()

        presenter.attachView(this)
        showProgressBar()
        presenter.getIssues()
    }

    private fun setupdapter(){
        adapter = IssuesAdapter(this, object : IssuesAdapter.OnSelectedListener{
            override fun onSelected(issue: Issue) {
                startActivity(DetailsViewActivity.newInstance(this@MainViewActivity,issue.number))
            }
        })
    }

    private fun setupRecyclerView(){

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if(layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount-1){

                    showProgressBar()
                    presenter.getIssues()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun setupswipeContainer(){
        swipeContainer.setOnRefreshListener {
            setStartRefresh()
            presenter.refresh()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun displayIssues(issues: List<Issue>) {
        adapter.IncludeIssues(issues)
    }

    override fun setStartRefresh() {
        swipeContainer.isRefreshing = true
    }

    override fun setEndRefresh() {
        swipeContainer.isRefreshing = false
    }

}
