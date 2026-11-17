package cl.bootcamp.leeconnos.data.modelos

data class Direccion(
    val calle: String,
    val comuna: String,
    val region: String,
    val telefono: String
) {
    override fun toString(): String {
        return "$calle, $comuna, $region"
    }
}