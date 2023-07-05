package com.example.gojekpractice.customviews

sealed class FullScreenViewType {
    object LoadingView: FullScreenViewType()
    object ErrorView: FullScreenViewType()
}