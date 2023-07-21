package com.example.mvidemo.v2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Отличие от v1 в том, что есть экшны
 * Это какбэ внутренние сущности, которые непосредственно меняют стейт
 */
abstract class MVIViewModel<
        E : MviEvent,
        S : MviState,
        A : MviAction
        >(
    private val initState: S
) : ViewModel() {

    /**
     * Тут принимаем ивенты из вью и маппим в какие-то флоу, но можно не в флоу
     */
    protected abstract fun processUiEvent(uiIntent: E): Flow<A>

    /**
     * Тут на основе экшна меняем стейт
     */
    protected abstract fun reduceState(state: S, action: A): S

    private val actions = MutableSharedFlow<A>()

    val uiState: StateFlow<S> = actions
        .scan(initState, ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initState)

    /**
     * Апи для вью - сюда прилетают ивенты
     */
    fun acceptUiEvent(uiEvent: E) {
        viewModelScope.launch {
            actions.emitAll(processUiEvent(uiEvent))
        }
    }

    /**
     * Внутреннее апи - один из вариантов создания новых экшэнов
     */
    protected fun emitAction(action: A) {
        viewModelScope.launch {
            actions.emit(action)
        }
    }
}