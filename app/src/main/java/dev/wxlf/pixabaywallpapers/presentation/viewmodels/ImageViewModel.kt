package dev.wxlf.pixabaywallpapers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.domain.usecases.FetchImageUseCase
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImageEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImageViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val fetchImageUseCase: FetchImageUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ImageViewState>()
    val viewState: LiveData<ImageViewState> get() = _viewState
    lateinit var imageEntity: ImageEntity

    fun obtainEvent(event: ImageEvent) {
        when (event) {
            is ImageEvent.LoadImage -> {
                viewModelScope.launch {
                    imageEntity = fetchImageUseCase.execute(event.id)
                    _viewState.postValue(
                        ImageViewState.ImageLoaded(imageEntity.largeImageURL)
                    )
                }
            }
        }
    }
}