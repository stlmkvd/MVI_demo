package com.example.mvidemo.v2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

abstract class MviFragment<
        I : MviEvent,
        S : MviState,
        VM : MVIViewModel<I, S, *>
        > : Fragment() {

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