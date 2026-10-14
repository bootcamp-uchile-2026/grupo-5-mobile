package cl.bootcamp.leeconnos.data.api

import retrofit2.Response
import retrofit2.http.GET

interface LeeconnosApi {

    @GET("libros")
    suspend fun getLibros(): Response<Any>
}