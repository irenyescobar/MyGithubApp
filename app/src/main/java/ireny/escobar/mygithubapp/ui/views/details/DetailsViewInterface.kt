package ireny.escobar.mygithubapp.ui.views.details

import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.ui.views.base.BaseViewInterface

interface DetailsViewInterface: BaseViewInterface {

    fun displayIssue(issue: Issue)
}