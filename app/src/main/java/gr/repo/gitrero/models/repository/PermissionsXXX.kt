package gr.repo.gitrero.models.repository


import com.google.gson.annotations.SerializedName

data class PermissionsXXX(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean
)