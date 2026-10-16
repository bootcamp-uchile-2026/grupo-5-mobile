package cl.bootcamp.leeconnos.data.mapeadores

import cl.bootcamp.leeconnos.data.api.dto.LibroDto
import cl.bootcamp.leeconnos.data.local.database.LibroEntity
import cl.bootcamp.leeconnos.data.modelos.Libro

fun LibroEntity.toDataClass(): Libro {

    return Libro(
        id = id,
        titulo = titulo,
        autor = autor,
        precio = precio,
        precioAnterior = precioAnterior,
        enOferta = enOferta,
        descuentoPorcentaje = descuentoPorcentaje,
        portadaUrl = portadaUrl,
        calificacion = calificacion,
        cantidadOpiniones = cantidadOpiniones,
        encuadernacion = encuadernacion,
        estaEnListaDeseados = enDeseados,
        cantidad = cantidadCarrito,
        descripcion = "",
        categorias = emptyList(),
        sinopsis = "",
        paginas = 0,
        videoUrl = ""
    )
}

fun Libro.toEntity(enDeseados: Boolean = this.estaEnListaDeseados, enCarrito: Boolean = false): LibroEntity {

    return LibroEntity(
        id = id,
        titulo = titulo,
        autor = autor,
        precio = precio,
        precioAnterior = precioAnterior,
        enOferta = enOferta,
        descuentoPorcentaje = descuentoPorcentaje,
        portadaUrl = portadaUrl,
        calificacion = calificacion,
        cantidadOpiniones = cantidadOpiniones,
        encuadernacion = encuadernacion,
        enDeseados = enDeseados,
        enCarrito = enCarrito,
        cantidadCarrito = cantidad
    )
}

fun LibroDto.toDataClass(): Libro {

    return Libro(
        id = this.id,
        titulo = this.titulo,
        autor = this.autor,
        descripcion = this.descripcion ?: "",
        precio = this.precio,
        precioAnterior = this.precioAnterior ?: this.precio,
        enOferta = this.enOferta ?: false,
        descuentoPorcentaje = this.descuentoPorcentaje ?: 0,
        portadaUrl = this.portadaUrl,
        categorias = this.categorias ?: emptyList(),
        sinopsis = this.sinopsis ?: this.descripcion ?: "Sin descripción disponible.",
        calificacion = this.calificacion ?: 0.0,
        cantidadOpiniones = this.opiniones ?: 0,
        encuadernacion = this.encuadernacion ?: "No especificada",
        paginas = this.paginas ?: 0,
        videoUrl = this.videoUrl ?: "",
        estaEnListaDeseados = false,
        cantidad = 1
    )
}

fun List<LibroDto>.toDataClassList(): List<Libro> {
    return this.map { it.toDataClass() }
}