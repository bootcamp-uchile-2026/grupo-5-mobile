package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class LibroDto(
    @SerializedName("id") val id: String,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("titulo") val titulo: String,
    @SerializedName("autor") val autor: String,
    @SerializedName("genero") val genero: String,
    @SerializedName("subgenero") val subgenero: String?,
    @SerializedName("categorias") val categorias: List<String>?,
    @SerializedName("sinopsis") val sinopsis: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("editorial") val editorial: String?,
    @SerializedName("anioPublicacion") val anioPublicacion: Int?,
    @SerializedName("formato") val formato: String?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("precio") val precio: Int,
    @SerializedName("precioAnterior") val precioAnterior: Int?,
    @SerializedName("descuentoPorcentaje") val descuentoPorcentaje: Int?,
    @SerializedName("enOferta") val enOferta: Boolean?,
    @SerializedName("stock") val stock: Int?,
    @SerializedName("enStock") val enStock: Boolean?,
    @SerializedName("portadaUrl") val portadaUrl: String,
    @SerializedName("calificacion") val calificacion: Double?,
    @SerializedName("opiniones") val opiniones: Int?,
    @SerializedName("ventas") val ventas: Int?,
    @SerializedName("encuadernacion") val encuadernacion: String?,
    @SerializedName("paginas") val paginas: Int?,
    @SerializedName("dimensiones") val dimensiones: String?,
    @SerializedName("etiquetas") val etiquetas: List<String>?,
    @SerializedName("videoUrl") val videoUrl: String?,
    @SerializedName("galeria") val galeria: List<String>?,
    @SerializedName("fechaIngreso") val fechaIngreso: String?
)
