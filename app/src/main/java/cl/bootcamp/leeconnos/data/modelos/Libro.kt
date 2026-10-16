package cl.bootcamp.leeconnos.data.modelos

data class Libro(
    val id: String,
    val titulo: String,
    val autor: String,
    val descripcion: String,
    val precio: Int,
    val precioAnterior: Int,
    val enOferta: Boolean,
    val portadaUrl: String,
    val categorias: List<String>,
    val sinopsis: String,
    val calificacion: Double,
    val cantidadOpiniones: Int,
    val encuadernacion: String,
    val paginas: Int,
    val videoUrl: String,
    var estaEnListaDeseados: Boolean = false,
    var cantidad: Int = 1
)
