package gr.repo.gitrero.models.repository


import com.google.gson.annotations.SerializedName

data class PermissionsX(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean
)