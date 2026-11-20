package cl.bootcamp.leeconnos.data.repositorio

import cl.bootcamp.leeconnos.data.api.LeeconnosApi
import cl.bootcamp.leeconnos.data.api.dto.ApiErrorDto
import cl.bootcamp.leeconnos.data.api.dto.AuthRequestDto
import cl.bootcamp.leeconnos.data.api.dto.RegistroRequestDto
import cl.bootcamp.leeconnos.data.local.datastore.AuthPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class AuthResult {
    object Success : AuthResult()
    data class Error(val mensaje: String) : AuthResult()
}

class AuthRepository(
    private val api: LeeconnosApi,
    private val authPreferences: AuthPreferences
) {

    val estaLogueado: Flow<Boolean> = authPreferences.accessToken.map { it != null }

    suspend fun login(email: String, contrasena: String): AuthResult {

        return try {

            val response = api.login(AuthRequestDto(email, contrasena))

            if (response.isSuccessful && response.body() != null) {

                val data = response.body()!!

                authPreferences.saveAuthData(
                    accessToken = data.accessToken,
                    refreshToken = data.refreshToken,
                    userId = data.usuario.id,
                    userName = data.usuario.nombreCompleto,
                    userEmail = data.usuario.email
                )

                AuthResult.Success

            } else {
                AuthResult.Error(parsearErrorApi(response.errorBody()?.string()))
            }

        } catch (e: Exception) {
            AuthResult.Error("Error de conexión. Revisa tu internet")
        }
    }

    suspend fun registrar(registroDto: RegistroRequestDto): AuthResult {

        return try {

            val response = api.registro(registroDto)

            if (response.isSuccessful && response.body() != null) {

                val data = response.body()!!

                authPreferences.saveAuthData(
                    accessToken = data.accessToken,
                    refreshToken = data.refreshToken,
                    userId = data.usuario.id,
                    userName = data.usuario.nombreCompleto,
                    userEmail = data.usuario.email
                )

                AuthResult.Success

            } else {
                AuthResult.Error(parsearErrorApi(response.errorBody()?.string()))
            }

        } catch(e: Exception) {

            AuthResult.Error("Error de conexión. Revisa tu internet.")
        }
    }

    suspend fun logout() {
        authPreferences.clearAuthData()

        //Llamar a API en un futuro cuando esté la opción de desloguearse desde mi cuenta (falta wireframe)
    }

    //Función para leer el body con el error
    private fun parsearErrorApi(errorBodyString: String?): String {

        if (errorBodyString == null || errorBodyString.isBlank()) return "Ocurrió un error inesperado (respuesta vacía)"

        android.util.Log.d("AUTH_REPO", "Error JSON recibido: $errorBodyString")

        return try {
            val errorDto = Gson().fromJson(errorBodyString, ApiErrorDto::class.java)

            errorDto.message ?: "Ocurrió un error inesperado en el servidor"

        } catch(e: Exception) {
            android.util.Log.e("AUTH_REPO", "Fallo al parsear JSON", e)
            "Ocurrió un error desconocido"
        }
    }
}