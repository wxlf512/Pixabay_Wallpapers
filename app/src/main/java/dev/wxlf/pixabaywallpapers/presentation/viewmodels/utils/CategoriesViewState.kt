package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils


sealed class CategoriesViewState {
    object LoadingCategories : CategoriesViewState()
    data class CategoriesLoaded(val categories: List<Pair<String, String>>) : CategoriesViewState()
    data class ErrorState(val message: String) : CategoriesViewState()
}