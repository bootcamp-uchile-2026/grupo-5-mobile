package cl.bootcamp.leeconnos.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LibroDao {

    // --- DESEADOS ---
    @Query("SELECT * FROM libros_locales WHERE enDeseados = 1")
    fun obtenerDeseados(): Flow<List<LibroEntity>>

    @Query("UPDATE libros_locales SET enDeseados = :enDeseados WHERE id = :libroId")
    suspend fun actualizarEstadoDeseado(libroId: String, enDeseados: Boolean)

    // --- CARRITO ---
    @Query("SELECT * FROM libros_locales WHERE enCarrito = 1")
    fun obtenerCarrito(): Flow<List<LibroEntity>>

    @Query("UPDATE libros_locales SET enCarrito = :enCarrito, cantidadCarrito = :cantidad WHERE id = :libroId")
    suspend fun actualizarEstadoCarrito(libroId: String, enCarrito: Boolean, cantidad: Int = 1)

    @Query("UPDATE libros_locales SET cantidadCarrito = :cantidad WHERE id = :libroId")
    suspend fun actualizarCantidadCarrito(libroId: String, cantidad: Int)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarOActualizar(libro: LibroEntity)

    @Query("SELECT * FROM libros_locales WHERE id = :libroId LIMIT 1")
    suspend fun obtenerPorId(libroId: String): LibroEntity?

    @Query("DELETE FROM libros_locales WHERE enDeseados = 0 AND enCarrito = 0")
    suspend fun limpiarHuerfanos() // Borra registros que ya no están ni en deseados ni en carrito
}