package cl.bootcamp.leeconnos.data.mapeadores

import cl.bootcamp.leeconnos.data.api.dto.CrearDireccionRequestDto
import cl.bootcamp.leeconnos.data.api.dto.DireccionDto
import cl.bootcamp.leeconnos.data.local.database.DireccionEntity
import cl.bootcamp.leeconnos.data.modelos.Direccion

fun DireccionDto.toEntity(): DireccionEntity {
    return DireccionEntity(
        id = this.id,
        alias = this.alias,
        nombreReceptor = this.nombreReceptor,
        calle = this.calle,
        comuna = this.comuna,
        ciudad = this.ciudad,
        region = this.region,
        codigoPostal = this.codigoPostal,
        telefono = this.telefono,
        rut = this.rut,
        predeterminada = this.predeterminada
    )
}

fun DireccionEntity.toDomain(): Direccion {
    return Direccion(
        id = this.id,
        alias = this.alias,
        calle = this.calle,
        comuna = this.comuna,
        ciudad = this.ciudad,
        region = this.region,
        codigoPostal = this.codigoPostal ?: "",
        telefono = this.telefono,
        rut = this.rut ?: "",
        detalle = "",
        informacionAdicional = "",
        nombreReceptor = this.nombreReceptor,
        predeterminada = this.predeterminada,
        // Solo local (pendiente implementación en Backend)
        latitud = 0.0,
        longitud = 0.0
    )
}

fun Direccion.toCreateRequest(): CrearDireccionRequestDto {

    val calleCompleta = if (this.detalle.isNotBlank()) "${this.calle}, ${this.detalle}" else this.calle

    return CrearDireccionRequestDto(
        alias = this.alias,
        predeterminada = false,
        nombreReceptor = this.nombreReceptor,
        calle = calleCompleta,
        comuna = this.comuna,
        ciudad = this.ciudad,
        region = this.region,
        codigoPostal = this.codigoPostal.ifBlank { null },
        telefono = this.telefono,
        rut = this.rut.ifBlank { null }
    )
}