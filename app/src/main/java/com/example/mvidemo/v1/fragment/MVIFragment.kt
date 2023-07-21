package com.example.mvidemo.v1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.mvidemo.v1.viewmodel.MVIViewModel

abstract class MVIFragment<
        UiState : com.example.mvidemo.v1.uistate.MVIState,
        UiEvent : com.example.mvidemo.v1.uievent.MVIEvent,
        VM : MVIViewModel<UiState, UiEvent>> :
    Fragment() {

    protected abstract fun provideViewModel(): VM

    @Composable
    protected abstract fun provideScreenContent(vm: VM): @Composable (VM) -> Unit

    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                provideScreenContent(provideViewModel())
            }
        }
    }
}