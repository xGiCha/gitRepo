package gr.repo.gitrero.adapters

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import gr.repo.gitrero.R
import gr.repo.gitrero.util.formatStringDateShort
import java.lang.reflect.Type
import java.util.*

object BindingAdapters {


    @BindingAdapter("id")
    @JvmStatic
    fun TextView.id(id: String) {
        text = String.format(resources.getString(R.string.repo_id), id)
    }

    @BindingAdapter(value = ["rvItem", "text"])
    @JvmStatic
    fun TextView.rvItem(rvItem: String, text: String) {
        val span = SpannableString(String.format(text, rvItem))
        span.setSpan(TextAppearanceSpan(context, R.style.textSpan), 0, text.length -4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = span
    }

    @BindingAdapter(value = ["rvItemDate", "text"])
    @JvmStatic
    fun TextView.rvItemDate(rvItemDate: String, text: String) {
        var dateFormat = formatStringDateShort(rvItemDate)
        val span = SpannableString(String.format(text, dateFormat))
        span.setSpan(TextAppearanceSpan(context, R.style.textSpan), 0, text.length -4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = span
    }

}