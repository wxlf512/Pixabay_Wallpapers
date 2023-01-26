package dev.wxlf.pixabaywallpapers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.stream.MalformedJsonException
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.domain.usecases.FetchImageUseCase
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImageEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImageViewState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val fetchImageUseCase: FetchImageUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ImageViewState>()
    val viewState: LiveData<ImageViewState> get() = _viewState
    private lateinit var imageEntity: ImageEntity

    init {
        _viewState.postValue(ImageViewState.LoadingImage)
    }
    fun obtainEvent(event: ImageEvent) {
        when (event) {
            is ImageEvent.LoadImage -> {
                viewModelScope.launch {
                    try {
                        imageEntity = fetchImageUseCase.execute(event.id)
                        _viewState.postValue(ImageViewState.ImageLoaded(imageEntity.largeImageURL))
                    } catch (e: HttpException) {
                        _viewState.postValue(ImageViewState.ErrorState("Ошибка ${e.code()}"))
                    } catch (e: UnknownHostException) {
                        _viewState.postValue(ImageViewState.ErrorState("Ошибка: Отсутствует подключение к сети"))
                    } catch (e: MalformedJsonException) {
                        _viewState.postValue(ImageViewState.ErrorState("Ошибка, возможно вы не используете VPN"))
                    } catch (e: Exception) {
                        _viewState.postValue(
                            ImageViewState.ErrorState(
                                e.localizedMessage ?: "Ошибка"
                            )
                        )
                    }

                }
            }
        }
    }
}