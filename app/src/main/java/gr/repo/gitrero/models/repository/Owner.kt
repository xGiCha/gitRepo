package gr.repo.gitrero.models.repository


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String
)