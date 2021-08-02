package gr.repo.gitrero.models.repository


import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner
)