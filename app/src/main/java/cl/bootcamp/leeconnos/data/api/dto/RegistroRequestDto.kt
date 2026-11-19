package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class RegistroRequestDto(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellido")
    val apellido: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val contrasena: String,
    @SerializedName("rol")
    val rol: String = "CLIENTE",
    @SerializedName("generosFavoritos")
    val generosFavoritos: List<String> = emptyList(),
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
    @SerializedName("recibirNovedades")
    val recibirNovedades: Boolean
)
