package cl.bootcamp.leeconnos.data.api

import cl.bootcamp.leeconnos.data.api.dto.LibrosPaginadosDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LeeconnosApi {

    @GET("libros")
    suspend fun getLibros(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 6
    ): Response<LibrosPaginadosDto>
}