package ireny.escobar.mygithubapp.model.services

import ireny.escobar.mygithubapp.model.entites.Issue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IssueService {

    @GET("issues")
    fun getIssues(@Query("page") page:Int): Call<List<Issue>>

    @GET("issues/{issue_number}")
    fun getIssue(@Path("issue_number") issue_number:Long): Call<Issue>

}