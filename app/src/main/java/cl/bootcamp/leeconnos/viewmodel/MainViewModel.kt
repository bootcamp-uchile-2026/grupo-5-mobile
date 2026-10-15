package cl.bootcamp.leeconnos.viewmodel

import androidx.lifecycle.ViewModel
import cl.bootcamp.leeconnos.data.modelos.Libro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _itemsCarrito = MutableStateFlow<List<Libro>>(emptyList())
    val itemsCarrito = _itemsCarrito.asStateFlow()

    private val _itemsListaDeseados = MutableStateFlow<List<Libro>>(emptyList())
    val itemsListaDeseados = _itemsListaDeseados.asStateFlow()

    //Carrito
    fun agregarItemCarrito(libro: Libro) {

        _itemsCarrito.update { listaActual ->

            val existe = listaActual.any { it.id == libro.id }

            if (existe) {

                listaActual.map { item ->

                    if (item.id == libro.id) {

                        val nuevaCantidad = if (item.cantidad < 99) item.cantidad + 1 else 99

                        item.copy(cantidad = nuevaCantidad)

                    } else {
                        item
                    }
                }

            } else {

                listaActual + libro.copy(cantidad = 1)
            }
        }
    }

    fun eliminarItemCarrito(libroId: String) {

        _itemsCarrito.update { listaActual ->

            listaActual.filter { it.id != libroId }
        }
    }

    fun disminuirCantidadItemCarrito(libro: Libro) {

        _itemsCarrito.update { listaActual ->

            listaActual.map { item ->

                if (item.id == libro.id) {

                    val nuevaCantidad = if (item.cantidad > 1) item.cantidad -1 else 1

                    item.copy(cantidad = nuevaCantidad)

                } else {
                    item
                }
            }
        }
    }

    //Lista de deseados
    fun agregarItemListaDeseados(libro: Libro) {
        _itemsListaDeseados.update { listaActual ->

            val existe = listaActual.any { it.id == libro.id }

            if (!existe) {
                val libroActualizado = libro.copy(estaEnListaDeseados = true)

                listaActual + libroActualizado

            } else {
                listaActual
            }
        }
    }


    fun eliminarItemListaDeseados(libroId: String) {

        _itemsListaDeseados.update { listaActual ->

            listaActual.filter { it.id != libroId }
        }
    }

}