package gr.repo.gitrero.models.commits


import com.google.gson.annotations.SerializedName

data class CommitsItem(
    @SerializedName("commit")
    val commit: Commit,
    @SerializedName("sha")
    val sha: String
)