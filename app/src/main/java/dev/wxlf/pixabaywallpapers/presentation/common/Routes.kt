package dev.wxlf.pixabaywallpapers.presentation.common

sealed class Routes(val route: String) {
    object Categories : Routes("pixwall://categories")
    object Images : Routes("pixwall://images")
}
