@file:Suppress("DEPRECATION")

package ireny.escobar.mygithubapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import ireny.escobar.mygithubapp.MyGitHubApplication
import ireny.escobar.mygithubapp.R
import ireny.escobar.mygithubapp.model.entites.Issue


fun Issue.formatStateText() : String {
    return if(this.state == "open")
        "Aberto"
    else "Fechado"
}

fun Issue.formatStateStyle(context:Context) : Drawable? {

    return if(state == "open"){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getDrawable(R.drawable.issue_opened)
        }else{
            context.resources.getDrawable(R.drawable.issue_opened)
        }
    }else{

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getDrawable(R.drawable.issue_closed)
        }else{
            context.resources.getDrawable(R.drawable.issue_closed)
        }
    }
}

val Activity.customApplication: MyGitHubApplication
    get() = application as MyGitHubApplication