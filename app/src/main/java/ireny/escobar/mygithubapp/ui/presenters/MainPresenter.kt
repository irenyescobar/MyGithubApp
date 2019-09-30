package ireny.escobar.mygithubapp.ui.presenters

import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.model.repositories.IssueRepositoryInterface
import ireny.escobar.mygithubapp.ui.views.main.MainViewInterface
import ireny.escobar.mygithubapp.utils.SchedulerProvider

class MainPresenter(private val issuesRepository: IssueRepositoryInterface,
                    private val schedulers: SchedulerProvider):
    BasePresenter<MainViewInterface>() {

    var page = 1

    fun getIssues() {
        execute()
    }

    fun refresh() {
        page = 1
        execute()
    }

    private fun execute(){
        val ds = issuesRepository.getIssues(page)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { issues -> onSuccess(issues) },
                { error -> onError(error) })

        disposable.add(ds)
    }


    private fun onSuccess(issues: List<Issue>) {
        if(issues.isNotEmpty()) {
            page += 1
            view?.displayIssues(issues)
        }

        view?.hideProgressBar()
        view?.setEndRefresh()
    }

    private fun onError(e: Throwable) {
        view?.hideProgressBar()
        view?.setEndRefresh()
        view?.displayError("Error: ${e.localizedMessage}")
    }
}