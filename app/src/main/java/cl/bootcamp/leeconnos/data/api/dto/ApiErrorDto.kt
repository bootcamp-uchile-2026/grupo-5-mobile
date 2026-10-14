package cl.bootcamp.leeconnos.data.api.dto

import com.google.gson.annotations.SerializedName

data class ApiErrorDto(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("detalles")
    val detalles: String? = null,
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("timestamp")
    val timestamp: String? = null,
    @SerializedName("path")
    val path: String? = null
)