package com.cuichen.common.mvp

interface BasePresenter<V : BaseView?> {
    fun attach(view: V)
    fun detach()
}