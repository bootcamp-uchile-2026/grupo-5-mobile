package cl.bootcamp.leeconnos.data.repositorio

import cl.bootcamp.leeconnos.data.local.datastore.ThemePreferences
import kotlinx.coroutines.flow.Flow

class ThemeRepository(
    private val themePreferences: ThemePreferences
) {
    val isDarkMode: Flow<Boolean?> = themePreferences.isDarkMode

    suspend fun setDarkMode(isDark: Boolean) {
        themePreferences.saveDarkMode(isDark)
    }
}