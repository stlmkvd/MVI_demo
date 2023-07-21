package com.example.mvidemo.v1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mvidemo.v1.uistate.MVIState
import com.example.mvidemo.v1.viewmodel.MVIViewModel

/**
 * Пример контента
 * Не как база, а просто общий вид функций
 * вместо [vm] подставляем нужного наследника
 *
 * Я за то, чтобы разделать скрин на 2 функции
 * 1 публичная - принимает вьюмодель, собирает стейт и связывает коллбеки с viewModel
 * 2 приватная - чисто апи экрана - стейт и коллбеки
 */
@Composable
fun Screen(vm: MVIViewModel<*, *>) {
    val state by vm.uiState.collectAsState()

    Screen(
        uiState = state
    ) {
        //                vm.acceptUiEvent(event: UiEvent)
    }
}

@Composable
private fun Screen(
    uiState: MVIState, callback1: () -> Unit
) {

}