package gr.repo.gitrero.models.commits


import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("sha")
    val sha: String,
    @SerializedName("url")
    val url: String
)