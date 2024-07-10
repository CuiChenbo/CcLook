package com.cuichen.cclook.mvp

import com.cuichen.common.mvp.BaseView

interface LoginView : BaseView {

    fun onResult(result: String?)
}