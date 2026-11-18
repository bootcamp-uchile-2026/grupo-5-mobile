package cl.bootcamp.leeconnos.data.modelos

data class Direccion(
    val calle: String = "",
    val comuna: String = "",
    val region: String = "",
    val telefono: String = "",
    val detalle: String = "",
    val informacionAdicional: String = "",
    val nombreReceptor: String = "",
    //Se guarda la latitud y longitud para poder centrar la cámara del mapa
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
) {
    override fun toString(): String {
        return "$calle, $comuna, $region"
    }
}