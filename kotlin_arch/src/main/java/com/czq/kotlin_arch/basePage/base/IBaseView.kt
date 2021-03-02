package com.czq.kotlin_arch.basePage.base

import android.content.Context
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider

interface IBaseView {

    fun showContent()
    fun showLoading()
    fun showEmpty()
    fun showError(it: Throwable? = null)
    fun getContext(): Context
    fun autoDispose(): AndroidLifecycleScopeProvider

}