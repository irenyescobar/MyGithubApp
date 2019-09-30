package ireny.escobar.mygithubapp.ui.presenters

import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.model.repositories.IssueRepositoryInterface
import ireny.escobar.mygithubapp.ui.views.details.DetailsViewInterface
import ireny.escobar.mygithubapp.utils.SchedulerProvider

class DetailsPresenter(private val issuesRepository: IssueRepositoryInterface,
                       private val schedulers: SchedulerProvider)
    : BasePresenter<DetailsViewInterface>() {

    fun getIssue(issueNumber: Long) {
        val ds = issuesRepository.getIssue(issueNumber)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { issues -> onSuccess(issues) },
                { error -> onError(error) })

        disposable.add(ds)
    }

    private fun onSuccess(issue: Issue) {
        view?.displayIssue(issue)
        view?.hideProgressBar()
    }

    private fun onError(e: Throwable) {
        view?.hideProgressBar()
        view?.displayError("Error: ${e.localizedMessage}")
    }
}