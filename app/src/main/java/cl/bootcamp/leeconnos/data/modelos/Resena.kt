package cl.bootcamp.leeconnos.data.modelos

data class Resena(

    val id: String,
    val autor: AutorResena,
    val tipo: String,
    val calificacion: Double,
    val titulo: String,
    val comentario: String,
    val verificada: Boolean,
    val fecha: String,
    val creadoEn: String,
    val actualizadoEn: String,
    val usuarioId: String,
    val libroId: String,
)