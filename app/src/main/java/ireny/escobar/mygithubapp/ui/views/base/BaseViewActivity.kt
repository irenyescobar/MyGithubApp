package ireny.escobar.mygithubapp.ui.views.base

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*

abstract class BaseViewActivity : AppCompatActivity(),
    BaseViewInterface {

    override fun showToast(s: String) {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun displayError(s: String) {
        showToast(s)
    }

}
