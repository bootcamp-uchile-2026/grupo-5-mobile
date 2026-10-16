package cl.bootcamp.leeconnos.data.repositorio

import cl.bootcamp.leeconnos.data.api.ApiClient
import cl.bootcamp.leeconnos.data.api.LeeconnosApi
import cl.bootcamp.leeconnos.data.local.database.LibroDao
import cl.bootcamp.leeconnos.data.mapeadores.toDataClass
import cl.bootcamp.leeconnos.data.mapeadores.toDataClassList
import cl.bootcamp.leeconnos.data.mapeadores.toEntity
import cl.bootcamp.leeconnos.data.modelos.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LibroRepository(
    private val api: LeeconnosApi = ApiClient.apiService,
    private val libroDao: LibroDao
) {

    val deseados: Flow<List<Libro>> = libroDao.obtenerDeseados()
        .map { lista -> lista.map { it.toDataClass() } }

    suspend fun toggleDeseado(libro: Libro): Boolean {
        val existente = libroDao.obtenerPorId(libro.id)
        return if (existente == null) {
            libroDao.insertarOActualizar(libro.toEntity(enDeseados = true, enCarrito = false))
            true
        } else {
            val nuevoEstado = !existente.enDeseados
            libroDao.actualizarEstadoDeseado(libro.id, nuevoEstado)
            if (!nuevoEstado) {
                libroDao.limpiarHuerfanos()
            }
            nuevoEstado
        }
    }

    val carrito: Flow<List<Libro>> = libroDao.obtenerCarrito()
        .map { lista -> lista.map { it.toDataClass() } }

    suspend fun agregarAlCarrito(libro: Libro, cantidad: Int = 1) {
        val existente = libroDao.obtenerPorId(libro.id)
        if (existente == null) {
            libroDao.insertarOActualizar(
                libro.toEntity(enDeseados = false, enCarrito = true).copy(cantidadCarrito = cantidad)
            )
        } else {
            val nuevaCantidad = if (existente.enCarrito) existente.cantidadCarrito + cantidad else cantidad
            libroDao.actualizarEstadoCarrito(libro.id, enCarrito = true, cantidad = nuevaCantidad)
        }
    }

    suspend fun modificarCantidadCarrito(libroId: String, delta: Int) {
        val existente = libroDao.obtenerPorId(libroId) ?: return
        val nuevaCantidad = existente.cantidadCarrito + delta
        if (nuevaCantidad > 0) {
            libroDao.actualizarCantidadCarrito(libroId, nuevaCantidad)
        } else {
            eliminarDelCarrito(libroId)
        }
    }

    suspend fun eliminarDelCarrito(libroId: String) {
        libroDao.actualizarEstadoCarrito(libroId, enCarrito = false, cantidad = 1)
        libroDao.limpiarHuerfanos()
    }


    suspend fun obtenerLibrosHome(page: Int = 1): Result<List<Libro>> {

        return withContext(Dispatchers.IO) {

            try {

                val response = api.getLibros(page = page)

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