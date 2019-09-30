package ireny.escobar.mygithubapp.model.entites

import java.util.*

data class Issue(val number:Long,
                 val title:String,
                 val body:String,
                 val created_at:Date,
                 val state: String,
                 val html_url:String,
                 val user: User
                 )