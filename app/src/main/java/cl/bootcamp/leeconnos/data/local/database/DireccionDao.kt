package cl.bootcamp.leeconnos.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DireccionDao {

    @Query("SELECT * FROM direcciones ORDER BY predeterminada DESC")
    fun obtenerDireccionesLocales(): Flow<List<DireccionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarDirecciones(direcciones: List<DireccionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarDireccion(direccion: DireccionEntity)

    @Query("DELETE FROM direcciones")
    suspend fun limpiarDirecciones()
}