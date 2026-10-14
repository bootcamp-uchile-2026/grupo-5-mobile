package cl.bootcamp.leeconnos.data.mapeadores

import cl.bootcamp.leeconnos.data.api.dto.LibroDto
import cl.bootcamp.leeconnos.data.modelos.Libro

fun LibroDto.toDataClass(): Libro {

    return Libro(
        id = this.id,
        titulo = this.titulo,
        autor = this.autor,
        precio = this.precio,
        portadaUrl = this.portadaUrl
    )
}

fun List<LibroDto>.toDataClassList(): List<Libro> {
    return this.map { it.toDataClass() }
}