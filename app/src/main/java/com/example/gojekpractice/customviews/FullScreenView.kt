package com.example.gojekpractice.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.gojekpractice.customviews.FullScreenViewType.ErrorView
import com.example.gojekpractice.customviews.FullScreenViewType.LoadingView
import com.example.gojekpractice.databinding.FullScreenViewBinding
import com.gopay.extensions.gone
import com.gopay.extensions.isGone
import com.gopay.extensions.isVisible
import com.gopay.extensions.visible

class FullScreenView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: FullScreenViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = FullScreenViewBinding.inflate(inflater, this, true)
    }

    fun showLoader(shouldShow: Boolean) {
        if (shouldShow && binding.loader.isGone()) {
            binding.loader.visible()
        } else if (!shouldShow && binding.loader.isVisible()) {
            binding.loader.gone()
        }
    }

    fun showError(shouldShow: Boolean, message: String?) {
        if (shouldShow) {
            if (binding.errorView.isGone()) {
                binding.errorView.visible()
            }
            if (binding.errorText.isGone()) {
                binding.errorText.visible()
                binding.errorText.text = message ?: "Something went wrong"
            }
            if (binding.retryButton.isGone()
            ) {
                binding.retryButton.visible()
            }
        } else {
            if (binding.errorView.isVisible()) {
                binding.errorView.gone()
            }
            if (binding.errorText.isVisible()) {
                binding.errorText.gone()
            }
            if (binding.retryButton.isVisible()) {
                binding.retryButton.gone()
            }
        }
    }

    fun show(type: FullScreenViewType) {
        when (type) {
            LoadingView -> {
                if (binding.loader.isGone()) {
                    binding.loader.visible()
                }
            }
            ErrorView -> {
                if (binding.loader.isVisible()) {
                    binding.loader.gone()
                }
                if (binding.errorView.isGone()) {
                    binding.errorView.visible()
                }
                if (binding.errorText.isGone()) {
                    binding.errorText.visible()
                }
            }
        }
    }

    fun hide(type: FullScreenViewType) {
        when (type) {
            LoadingView -> {
                if (binding.loader.isVisible()) {
                    binding.loader.gone()
                }
            }
            ErrorView -> {
                if (binding.errorView.isVisible()) {
                    binding.errorView.gone()
                }
                if (binding.errorText.isVisible()) {
                    binding.errorText.gone()
                }
            }
        }
    }

}