package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("tokenType")
    val tokenType: String,
    @SerializedName("expiresIn")
    val expiresIn: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("refreshExpiresIn")
    val refreshExpiresIn: String,
    @SerializedName("usuario") 
    val usuario: UsuarioDto,
    @SerializedName("tokenVerificacionDesarrollo")
    val tokenVerificacionDesarrollo: String?
)
