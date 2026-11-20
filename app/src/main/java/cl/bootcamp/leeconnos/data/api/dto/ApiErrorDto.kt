package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class ApiErrorDto(
    @SerializedName("statusCode")
    val statusCode: Int? = null,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("detalles")
    val detalles: List<String>? = null,
    @SerializedName("requestId")
    val requestId: String? = null,
    @SerializedName("timestamp")
    val timestamp: String? = null,
    @SerializedName("path")
    val path: String? = null
)