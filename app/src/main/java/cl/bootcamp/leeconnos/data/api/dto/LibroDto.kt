package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class LibroDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("autor")
    val autor: String,
    @SerializedName("genero")
    val genero: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("editorial")
    val editorial: String,
    @SerializedName("anioPublicacion")
    val anioPublicacion: Int,
    @SerializedName("formato")
    val formato: String,
    @SerializedName("estado")
    val estado: String,
    @SerializedName("precio")
    val precio: Int,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("enStock")
    val enStock: Boolean,
    @SerializedName("portadaUrl")
    val portadaUrl: String,
    @SerializedName("fechaIngreso")
    val fechaIngreso: String
)
