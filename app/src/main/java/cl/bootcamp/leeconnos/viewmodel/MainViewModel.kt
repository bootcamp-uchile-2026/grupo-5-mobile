package cl.bootcamp.leeconnos.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _cantidadItemsCarrito = MutableStateFlow(0)
    val cantidadItemsCarrito: StateFlow<Int> = _cantidadItemsCarrito.asStateFlow()

    private val _cantidadItemsListaDeseados = MutableStateFlow(0)
    val cantidadItemsListaDeseados: StateFlow<Int> = _cantidadItemsListaDeseados.asStateFlow()

    //Carrito
    fun agregarItemCarrito() {
        _cantidadItemsCarrito.value += 1
    }

    fun eliminarItemCarrito() {
        _cantidadItemsCarrito.value -= 1
    }

    //Lista de deseados

    fun agregarItemListaDeseados() {
        _cantidadItemsListaDeseados.value += 1
    }

    fun eliminarItemListaDeseados() {
        _cantidadItemsListaDeseados.value -= 1
    }

}