package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class AuthRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val contrasena: String
)
