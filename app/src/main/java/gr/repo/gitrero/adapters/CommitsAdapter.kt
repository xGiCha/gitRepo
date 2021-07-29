package gr.repo.gitrero.adapters

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.repo.gitrero.R
import gr.repo.gitrero.adapters.BindingAdapters.rvItem
import gr.repo.gitrero.databinding.CommitItemBinding
import gr.repo.gitrero.models.commits.Commits
import gr.repo.gitrero.models.commits.CommitsItem

class CommitsAdapter(
        val context: Context,
        val commits: MutableList<CommitsItem>)

    : RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    private lateinit var parentContext: Context

    override fun getItemCount() = commits.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding: CommitItemBinding = CommitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
}

    inner class ViewHolder(val binding: CommitItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommitsItem, position: Int) {

            binding.commit = item
//            var str = String.format(context.getString(R.string.sha_t), item.sha)
//            val span = SpannableString(str)
//            span.setSpan(TextAppearanceSpan(context, R.style.textSpan), 0, str.length -1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//            binding.shaTxtV.text = span
        }
    }


    fun updateItems(newItems: List<CommitsItem>) {
        commits.clear()
        commits.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commits[position], position)
    }

}