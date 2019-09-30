package ireny.escobar.mygithubapp.model.repositories

import io.reactivex.Single
import io.reactivex.SingleEmitter
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.model.services.IssueService

class IssueRepository(private val issueService: IssueService):IssueRepositoryInterface {

    override fun getIssues(page: Int): Single<List<Issue>> {
        return Single.create { emitter: SingleEmitter<List<Issue>> ->
            loadIssues(page, emitter)
        }
    }

    private fun loadIssues(page: Int, emitter: SingleEmitter<List<Issue>>) {
        try {
            val issues = issueService.getIssues(page).execute().body()
            if (issues != null) {
                emitter.onSuccess(issues)
            } else {
                emitter.onError(Exception("No data"))
            }
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }

    override fun getIssue(issue_number: Long): Single<Issue> {
        return Single.create { emitter: SingleEmitter<Issue> ->
            loadIssue(issue_number, emitter)
        }
    }

    private fun loadIssue(issue_number: Long, emitter: SingleEmitter<Issue>) {
        try {
            val issue = issueService.getIssue(issue_number).execute().body()
            if (issue != null) {
                emitter.onSuccess(issue)
            } else {
                emitter.onError(Exception("No data"))
            }
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }
}