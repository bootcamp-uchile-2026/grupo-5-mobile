package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class DireccionDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("nombreReceptor")
    val nombreReceptor: String,
    @SerializedName("calle")
    val calle: String,
    @SerializedName("comuna")
    val comuna: String,
    @SerializedName("ciudad")
    val ciudad: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("codigoPostal")
    val codigoPostal: String?,
    @SerializedName("telefono")
    val telefono: String,
    @SerializedName("rut")
    val rut: String?,
    @SerializedName("predeterminada")
    val predeterminada: Boolean,
    @SerializedName("creadoEn")
    val creadoEn: String
)

data class CrearDireccionRequestDto(
    @SerializedName("alias")
    val alias: String,
    @SerializedName("predeterminada")
    val predeterminada: Boolean,
    @SerializedName("nombreReceptor")
    val nombreReceptor: String,
    @SerializedName("calle")
    val calle: String,
    @SerializedName("comuna")
    val comuna: String,
    @SerializedName("ciudad")
    val ciudad: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("codigoPostal")
    val codigoPostal: String?,
    @SerializedName("telefono")
    val telefono: String,
    @SerializedName("rut")
    val rut: String?
)