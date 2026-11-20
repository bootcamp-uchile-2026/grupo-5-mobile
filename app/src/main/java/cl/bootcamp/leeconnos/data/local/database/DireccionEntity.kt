package cl.bootcamp.leeconnos.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "direcciones")
data class DireccionEntity(
    @PrimaryKey
    val id: String,
    val alias: String,
    val nombreReceptor: String,
    val calle: String,
    val comuna: String,
    val ciudad: String,
    val region: String,
    val codigoPostal: String?,
    val telefono: String,
    val rut: String?,
    val predeterminada: Boolean
)
