package dev.wxlf.pixabaywallpapers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.stream.MalformedJsonException
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.pixabaywallpapers.domain.usecases.FetchCategoryUseCase
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImagesEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImagesViewState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val fetchCategoryUseCase: FetchCategoryUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ImagesViewState>()
    val viewState: LiveData<ImagesViewState> get() = _viewState

    init {
        _viewState.postValue(ImagesViewState.LoadingCategory)
    }

    fun obtainEvent(event: ImagesEvent) {
        when (event) {
            is ImagesEvent.LoadCategory -> {
                viewModelScope.launch {
                    try {
                        val images = fetchCategoryUseCase.execute(event.category)
                        _viewState.postValue(ImagesViewState.CategoryLoaded(images))
                    } catch (e: HttpException) {
                        _viewState.postValue(ImagesViewState.ErrorState("Ошибка ${e.code()}"))
                    } catch (e: UnknownHostException) {
                        _viewState.postValue(ImagesViewState.ErrorState("Ошибка: Отсутствует подключение к сети"))
                    } catch (e: MalformedJsonException) {
                        _viewState.postValue(ImagesViewState.ErrorState("Ошибка, возможно вы не используете VPN"))
                    } catch (e: Exception) {
                        _viewState.postValue(
                            ImagesViewState.ErrorState(
                                e.localizedMessage ?: "Ошибка"
                            )
                        )
                    }
                }
            }
        }
    }

}