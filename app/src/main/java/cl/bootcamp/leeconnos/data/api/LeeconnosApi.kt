package cl.bootcamp.leeconnos.data.api

import cl.bootcamp.leeconnos.data.api.dto.AuthRequestDto
import cl.bootcamp.leeconnos.data.api.dto.AuthResponseDto
import cl.bootcamp.leeconnos.data.api.dto.LibrosPaginadosDto
import cl.bootcamp.leeconnos.data.api.dto.RegistroRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LeeconnosApi {

    @GET("libros")
    suspend fun getLibros(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 6
    ): Response<LibrosPaginadosDto>

    @POST("auth/login")
    suspend fun login(
        @Body request: AuthRequestDto
    ): Response<AuthResponseDto>

    @POST("auth/registro")
    suspend fun registro(
        @Body request: RegistroRequestDto
    ): Response<AuthResponseDto>
}