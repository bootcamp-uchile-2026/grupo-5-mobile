package cl.bootcamp.leeconnos.data.repositorio

import android.util.Log
import cl.bootcamp.leeconnos.data.api.LeeconnosApi
import cl.bootcamp.leeconnos.data.api.dto.ApiErrorDto
import cl.bootcamp.leeconnos.data.local.database.DireccionDao
import cl.bootcamp.leeconnos.data.mapeadores.toCreateRequest
import cl.bootcamp.leeconnos.data.mapeadores.toDomain
import cl.bootcamp.leeconnos.data.mapeadores.toEntity
import cl.bootcamp.leeconnos.data.modelos.Direccion
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class DireccionResult {
    object Success : DireccionResult()
    data class Error(val mensaje: String) : DireccionResult()
}

class DireccionRepository(
    private val api: LeeconnosApi,
    private val direccionDao: DireccionDao
) {
    //Leemos siempre desde la base de datos local
    val direccionesLocales: Flow<List<Direccion>> = direccionDao.obtenerDireccionesLocales()
        .map { listaEntities ->
            listaEntities.map { it.toDomain() }
        }

    //Obtener direcciones actualizadas del servidor para guardarlas en room
    suspend fun sincronizarDireccionesDesdeServidor() {
        try {
            val response = api.obtenerDirecciones()
            if (response.isSuccessful && response.body() != null) {
                val direccionesDto = response.body()!!

                direccionDao.limpiarDirecciones()

                val entidades = direccionesDto.map { it.toEntity() }
                direccionDao.guardarDirecciones(entidades)
            } else {
                Log.e("DIR_REPO", "Error al sincronizar: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("DIR_REPO", "Error de red al sincronizar", e)
        }
    }

    suspend fun crearDireccion(direccion: Direccion): DireccionResult {
        return try {
            val requestDto = direccion.toCreateRequest()
            val response = api.crearDireccion(requestDto)

            if (response.isSuccessful && response.body() != null) {

                val nuevaDireccionGuardada = response.body()!!
                direccionDao.guardarDireccion(nuevaDireccionGuardada.toEntity())

                DireccionResult.Success
            } else {
                val errorMsg = parsearErrorApi(response.errorBody()?.string())
                DireccionResult.Error(errorMsg)
            }
        } catch (e: Exception) {
            DireccionResult.Error("Error de conexión al crear dirección.")
        }
    }

    private fun parsearErrorApi(errorBodyString: String?): String {
        if (errorBodyString.isNullOrBlank()) return "Error inesperado"

        return try {
            val errorLimpio = errorBodyString.trim()
            if (errorLimpio.startsWith("{")) {
                Gson().fromJson(errorLimpio, ApiErrorDto::class.java).message ?: "Error del servidor"
            } else {
                errorLimpio.removeSurrounding("\"")
            }
        } catch (e: Exception) {
            "Error desconocido"
        }
    }
}