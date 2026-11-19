package cl.bootcamp.leeconnos.utils


//Necesario ya que el endpoint pide solo las siglas y la api de Google Maps y places devuelven el nombre completo
fun obtenerSiglaRegion(nombreRegionGoogle: String): String {
    val regionNormalizada = nombreRegionGoogle.lowercase()

    return when {
        regionNormalizada.contains("metropolitana") -> "RM"
        regionNormalizada.contains("arica") || regionNormalizada.contains("parinacota") -> "XV"
        regionNormalizada.contains("tarapacá") || regionNormalizada.contains("tarapaca") -> "I"
        regionNormalizada.contains("antofagasta") -> "II"
        regionNormalizada.contains("atacama") -> "III"
        regionNormalizada.contains("coquimbo") -> "IV"
        regionNormalizada.contains("valparaíso") || regionNormalizada.contains("valparaiso") -> "V"
        regionNormalizada.contains("higgins") -> "VI" // O'Higgins
        regionNormalizada.contains("maule") -> "VII"
        regionNormalizada.contains("ñuble") -> "XVI"
        regionNormalizada.contains("biobío") || regionNormalizada.contains("biobio") -> "VIII"
        regionNormalizada.contains("araucanía") || regionNormalizada.contains("araucania") -> "IX"
        regionNormalizada.contains("ríos") || regionNormalizada.contains("rios") -> "XIV"
        regionNormalizada.contains("lagos") -> "X"
        regionNormalizada.contains("aysén") || regionNormalizada.contains("aysen") -> "XI"
        regionNormalizada.contains("magallanes") -> "XII"
        else -> "RM"
    }
}