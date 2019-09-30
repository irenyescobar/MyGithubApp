package ireny.escobar.mygithubapp.ui.views.main

import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.ui.views.base.BaseViewInterface

interface MainViewInterface : BaseViewInterface {

    fun displayIssues(issues: List<Issue>)
    fun setStartRefresh()
    fun setEndRefresh()
}