package cl.bootcamp.leeconnos.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import cl.bootcamp.leeconnos.data.modelos.Direccion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class MetodoEntrega {
    NINGUNO,
    DELIVERY,
    RETIRO_TIENDA
}

enum class MetodoPago {
    NINGUNO,
    TARJETA,
    TRANSFERENCIA
}

data class CheckoutUiState(
    val mostrarDialogoEntrega: Boolean = false,
    val mostrarDialogoTarjeta: Boolean = false,

    val metodoEntrega: MetodoEntrega = MetodoEntrega.NINGUNO,
    val metodoPago: MetodoPago = MetodoPago.NINGUNO,

    val direccionSeleccionada: Direccion? = null,
    val textoCupon: String = "",

    val costoDespacho: Int = 0,
    val descuentoCupon: Int = 0,
    val totalCompra: Int = 0
){
    val puedePagar: Boolean
        get() = metodoEntrega != MetodoEntrega.NINGUNO && metodoPago != MetodoPago.NINGUNO
}

class CheckoutViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    //Dialogos
    fun cambiarMostrarDialogoEntrega(mostrar: Boolean) {
        _uiState.update { it.copy(mostrarDialogoEntrega = mostrar) }
    }

    fun cambiarMostrarDialogoTarjeta(mostrar: Boolean) {
        _uiState.update { it.copy(mostrarDialogoTarjeta = mostrar) }
    }

    //Elección de usuario
    fun seleccionarMetodoEntrega(metodo: MetodoEntrega) {

        _uiState.update { state ->
            state.copy(
                metodoEntrega = metodo,
                mostrarDialogoEntrega = metodo == MetodoEntrega.DELIVERY //Por ahora solo en delivery por falta de wireframe completo
            )
        }
    }

    fun seleccionarMetodoPago(metodo: MetodoPago) {

        _uiState.update { state ->
            state.copy(
                metodoPago = metodo,
                mostrarDialogoTarjeta = metodo == MetodoPago.TARJETA //Por ahora solo tarjeta por falta de wireframe completo
            )
        }
    }

    fun actualizarTextoCupon(texto: String) {
        _uiState.update { it.copy(textoCupon = texto) }
    }

    fun confirmarDireccion(direccion: Direccion) {

        _uiState.update { state ->
            state.copy(
                direccionSeleccionada = direccion,
                mostrarDialogoEntrega = false,
                costoDespacho = 4000 //Por ahora hardcodeado, cambiar cuando haya validación a futuro
            )
        }
    }

    fun calcularTotalCompra(totalProductos: Int) {

        val total = totalProductos + uiState.value.costoDespacho

        _uiState.update { it.copy(totalCompra = aplicarDescuento(total)) }
    }

    fun aplicarDescuento(subtotal: Int): Int {

        return if (uiState.value.descuentoCupon > 0) {
            subtotal - (subtotal * (uiState.value.descuentoCupon / 100))

        } else {
            subtotal
        }
    }
}