package cl.bootcamp.leeconnos.data.modelos

data class Libro(
    val id: String,
    val titulo: String,
    val autor: String,
    val precio: Int,
    val portadaUrl: String,
    val estaEnListaDeseados: Boolean = false,
    val cantidad: Int = 0
)
