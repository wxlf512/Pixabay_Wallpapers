package dev.wxlf.pixabaywallpapers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.pixabaywallpapers.domain.usecases.FetchCategoryUseCase
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImagesEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImagesViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val fetchCategoryUseCase: FetchCategoryUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ImagesViewState>()
    val viewState: LiveData<ImagesViewState> get() = _viewState

    fun obtainEvent(event: ImagesEvent) {
        when (event) {
            is ImagesEvent.LoadCategory -> {
                viewModelScope.launch {
                    _viewState.postValue(
                        ImagesViewState.CategoryLoaded(
                            fetchCategoryUseCase.execute(
                                event.category
                            )
                        )
                    )
                }
            }
        }
    }

}