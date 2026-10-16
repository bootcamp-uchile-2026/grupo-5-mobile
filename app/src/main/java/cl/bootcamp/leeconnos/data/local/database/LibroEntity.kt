package cl.bootcamp.leeconnos.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libros_locales")
data class LibroEntity(
    @PrimaryKey
    val id: String,
    val titulo: String,
    val autor: String,
    val precio: Int,
    val precioAnterior: Int,
    val enOferta: Boolean,
    val descuentoPorcentaje: Int,
    val portadaUrl: String,
    val calificacion: Double,
    val cantidadOpiniones: Int,
    val encuadernacion: String,

    // Flags de persistencia local
    val enDeseados: Boolean = false,
    val enCarrito: Boolean = false,
    val cantidadCarrito: Int = 1
)
