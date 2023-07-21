package com.example.mvidemo.v2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

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
fun Screen(vm: MVIViewModel<*, *, *>) {
    val state by vm.uiState.collectAsState()

    Screen(
        uiState = state
    ) {
        //                vm.acceptUiEvent(event: UiEvent)
    }
}

@Composable
private fun Screen(
    uiState: MviState, callback1: () -> Unit
) {

}