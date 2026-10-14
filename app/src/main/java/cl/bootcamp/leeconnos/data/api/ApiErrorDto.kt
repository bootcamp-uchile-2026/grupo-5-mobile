package cl.bootcamp.leeconnos.data.api

import com.google.gson.annotations.SerializedName

data class ApiErrorDto(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("error") val error: String,
    @SerializedName("message") val message: String,
    @SerializedName("detalles") val detalles: String? = null,
    @SerializedName("requestId") val requestId: String
)
