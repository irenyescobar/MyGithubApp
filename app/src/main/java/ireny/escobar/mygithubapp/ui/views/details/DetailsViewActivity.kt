package ireny.escobar.mygithubapp.ui.views.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import ireny.escobar.mygithubapp.R
import ireny.escobar.mygithubapp.di.modules.DetailsModule
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.ui.views.base.BaseViewActivity
import ireny.escobar.mygithubapp.ui.presenters.DetailsPresenter
import ireny.escobar.mygithubapp.utils.CircleTransform
import ireny.escobar.mygithubapp.utils.LOCALE_BRAZIL
import kotlinx.android.synthetic.main.activity_details_view.*
import java.text.SimpleDateFormat

import ireny.escobar.mygithubapp.utils.formatStateText
import ireny.escobar.mygithubapp.utils.formatStateStyle
import ireny.escobar.mygithubapp.utils.customApplication

class DetailsViewActivity : BaseViewActivity(),DetailsViewInterface{

    private val presenter: DetailsPresenter by lazy { component.presenter() }
    private val component by lazy { customApplication.component.plus(DetailsModule()) }

    private var currentIssue: Issue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setContentView(R.layout.activity_details_view)

        button.setOnClickListener{
            openUrl()
        }

        presenter.attachView(this)
        val issueNumber = intent.getLongExtra(ISSUE_NUMBER,-1)
        showProgressBar()
        presenter.getIssue(issueNumber)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun displayIssue(issue: Issue) {

        currentIssue = issue

        currentIssue?.also {
            Glide.with(this)
                .load(it.user.avatar_url)
                .transform(CircleTransform())
                .into(imgAvatar)
            tvLogin.text = it.user.login
            tvTitle.text = it.title
            tvState.text= it.formatStateText()
            tvState.background = it.formatStateStyle(this)
            tvCreatedAt.text =  SimpleDateFormat("dd/MM/yyyy", LOCALE_BRAZIL).format(it.created_at)
            tvBody.text = it.body
        }

    }

    fun openUrl(){

        currentIssue?.also {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url))

            if (intent.resolveActivity(this.packageManager) != null) {
                startActivity(intent)
            }
        }

    }

    companion object{
        const val ISSUE_NUMBER = "ISSUE_NUMBER"

        @JvmStatic
        fun newInstance(context: Context, issueId:Long) =
            Intent(context, DetailsViewActivity::class.java).apply {
                putExtra(ISSUE_NUMBER,issueId)
            }
    }

}
