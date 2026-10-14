package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class LibrosPaginadosDto(
    @SerializedName("items")
    val items: List<LibroDto>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)
