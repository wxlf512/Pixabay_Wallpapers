package dev.wxlf.pixabaywallpapers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.stream.MalformedJsonException
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.pixabaywallpapers.domain.usecases.FetchCategoryImageUseCase
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.CategoriesEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.CategoriesViewState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val fetchCategoryImageUseCase: FetchCategoryImageUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<CategoriesViewState>()
    val viewState: LiveData<CategoriesViewState> get() = _viewState

    private val categories = listOf(
        "backgrounds",
        "fashion",
        "nature",
        "science",
        "education",
        "feelings",
        "health",
        "people",
        "religion",
        "places",
        "animals",
        "industry",
        "computer",
        "food",
        "sports",
        "transportation",
        "travel",
        "buildings",
        "business",
        "music"
    )

    init {
        _viewState.postValue(CategoriesViewState.LoadingCategories)
    }

    fun obtainEvent(event: CategoriesEvent) {
        when (event) {
            CategoriesEvent.LoadCategories -> {
                viewModelScope.launch {
                    try {
                        val categoriesMap: MutableList<Pair<String, String>> = mutableListOf()
                        categories.forEach {
                            categoriesMap.add(it to fetchCategoryImageUseCase.execute(it).webformatURL)
                        }
                        _viewState.postValue(CategoriesViewState.CategoriesLoaded(categoriesMap))
                    } catch (e: HttpException) {
                        _viewState.postValue(CategoriesViewState.ErrorState("Ошибка ${e.code()}"))
                    } catch (e: UnknownHostException) {
                        _viewState.postValue(CategoriesViewState.ErrorState("Ошибка: Отсутствует подключение к сети"))
                    } catch (e: MalformedJsonException) {
                        _viewState.postValue(CategoriesViewState.ErrorState("Ошибка, возможно вы не используете VPN"))
                    } catch (e: Exception) {
                        _viewState.postValue(
                            CategoriesViewState.ErrorState(
                                e.localizedMessage ?: "Ошибка"
                            )
                        )
                    }
                }
            }
        }
    }

}