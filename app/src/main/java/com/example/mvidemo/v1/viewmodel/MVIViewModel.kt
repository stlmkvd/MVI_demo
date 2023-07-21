package com.example.mvidemo.v1.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class MVIViewModel<
        UiState : com.example.mvidemo.v1.uistate.MVIState,
        UiEvent : com.example.mvidemo.v1.uievent.MVIEvent
        >(
    initialState: UiState,
) : ViewModel() {

    private val mutableUiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = mutableUiState

    fun acceptUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            //Тут в when блоке перебираем пришедшие uiEvent'ы по типу и как-то реагируем
        }
    }

    /**
     * Вызывается для изменения стейтов
     */
    protected fun updateState(updateAction: (UiState) -> UiState) {
        mutableUiState.update { uiState ->
            updateAction(uiState)
        }
    }

    /**
     * Можно использовать, чтобы дернуть стейт и что-то с ним сделать
     */
    protected fun <R> withState(block: (UiState) -> R): R {
        return block.invoke(mutableUiState.value)
    }
}