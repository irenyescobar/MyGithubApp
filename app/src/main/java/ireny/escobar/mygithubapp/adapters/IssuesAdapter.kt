package ireny.escobar.mygithubapp.adapters

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import ireny.escobar.mygithubapp.R
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.utils.formatStateText
import ireny.escobar.mygithubapp.utils.formatStateStyle



class IssuesAdapter(private val context: Context, private val listener: OnSelectedListener): RecyclerView.Adapter<IssuesAdapter.IssuesHolder>() {

    private var issuesList: MutableList<Issue> = mutableListOf()

    fun IncludeIssues(list:List<Issue>){
        issuesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesHolder {

        val v = LayoutInflater.from(context).inflate(R.layout.item_issue, parent, false)
        return IssuesHolder(v)
    }

    override fun getItemCount(): Int = issuesList.size

    override fun onBindViewHolder(holder: IssuesHolder, position: Int) {

        val issue: Issue = issuesList[position]

        holder.itemView.setOnClickListener{
            listener.onSelected(issue)
        }

        holder.tvTitle.text =  issuesList[position].title
        holder.tvStatus.background = issue.formatStateStyle(context)
        holder.tvStatus.text = issue.formatStateText()
    }


    class IssuesHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        var tvStatus: TextView = v.findViewById(R.id.tvState)
    }

    interface OnSelectedListener {
        fun onSelected(issue: Issue)
    }
}