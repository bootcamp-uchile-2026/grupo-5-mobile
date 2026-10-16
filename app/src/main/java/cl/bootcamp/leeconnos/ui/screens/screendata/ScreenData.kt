package cl.bootcamp.leeconnos.ui.screens.screendata

interface ScreenData {
    val route: String
    val title: String
    val icon: Int?
    val showGlobalTopBar: Boolean
    val showBottomBar: Boolean
}