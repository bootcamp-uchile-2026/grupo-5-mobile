package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class UsuarioDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellido")
    val apellido: String,
    @SerializedName("nombreCompleto")
    val nombreCompleto: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("rol")
    val rol: String,
    @SerializedName("generosFavoritos")
    val generosFavoritos: List<String>,
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
    @SerializedName("recibirNovedades")
    val recibirNovedades: Boolean,
    @SerializedName("emailVerificado")
    val emailVerificado: Boolean,
    @SerializedName("proveedorAuth")
    val proveedorAuth: String,
    @SerializedName("tieneContrasena")
    val tieneContrasena: Boolean,
    @SerializedName("fechaRegistro")
    val fechaRegistro: String,
    @SerializedName("creadoEn")
    val creadoEn: String
)
