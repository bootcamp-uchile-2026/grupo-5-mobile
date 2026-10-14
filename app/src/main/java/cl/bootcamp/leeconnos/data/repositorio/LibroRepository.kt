package cl.bootcamp.leeconnos.data.repositorio

import cl.bootcamp.leeconnos.data.api.ApiClient
import cl.bootcamp.leeconnos.data.api.LeeconnosApi
import cl.bootcamp.leeconnos.data.mapeadores.toDataClassList
import cl.bootcamp.leeconnos.data.modelos.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibroRepository(
    private val api: LeeconnosApi = ApiClient.apiService
) {

    suspend fun obtenerLibrosHome(): Result<List<Libro>> {

        return withContext(Dispatchers.IO) {

            try {

                val response = api.getLibros()

                if (response.isSuccessful && response.body() != null) {

                    val libros = response.body()!!.items.toDataClassList()
                    Result.success(libros)

                } else {
                    Result.failure(Exception("Error HTTP: ${response.code()}"))
                }

            } catch (e: Exception) {
                Result.failure(Exception("Verifica tu conexión a internet"))
            }
        }
    }
}