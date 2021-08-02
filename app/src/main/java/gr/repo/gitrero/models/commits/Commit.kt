package gr.repo.gitrero.models.commits


import com.google.gson.annotations.SerializedName

data class Commit(
    @SerializedName("author")
    val author: AuthorX,
    @SerializedName("message")
    val message: String,
    @SerializedName("url")
    val url: String
)