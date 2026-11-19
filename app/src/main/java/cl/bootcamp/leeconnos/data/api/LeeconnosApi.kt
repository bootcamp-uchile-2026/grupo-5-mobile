package cl.bootcamp.leeconnos.data.api

import cl.bootcamp.leeconnos.data.api.dto.AuthRequestDto
import cl.bootcamp.leeconnos.data.api.dto.AuthResponseDto
import cl.bootcamp.leeconnos.data.api.dto.CrearDireccionRequestDto
import cl.bootcamp.leeconnos.data.api.dto.DireccionDto
import cl.bootcamp.leeconnos.data.api.dto.LibrosPaginadosDto
import cl.bootcamp.leeconnos.data.api.dto.RegistroRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LeeconnosApi {

    //Autorización
    @POST("auth/login")
    suspend fun login(
        @Body request: AuthRequestDto
    ): Response<AuthResponseDto>

    @POST("auth/registro")
    suspend fun registro(
        @Body request: RegistroRequestDto
    ): Response<AuthResponseDto>

    //Libros
    @GET("libros")
    suspend fun getLibros(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 6
    ): Response<LibrosPaginadosDto>

    //Direcciones
    @GET("direcciones")
    suspend fun obtenerDirecciones(): Response<List<DireccionDto>>

    @POST("direcciones")
    suspend fun crearDireccion(
        @Body request: CrearDireccionRequestDto
    ): Response<DireccionDto>
}