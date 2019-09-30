package ireny.escobar.mygithubapp.model.repositories

import io.reactivex.Single
import ireny.escobar.mygithubapp.model.entites.Issue

interface IssueRepositoryInterface {
    fun getIssues(page: Int = 1): Single<List<Issue>>
    fun getIssue(issue_number:Long): Single<Issue>
}