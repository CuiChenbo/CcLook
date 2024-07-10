package com.cuichen.cclook.mvp

import com.cuichen.common.mvp.BaseView

interface MyView : BaseView {

    fun onResult(result: String?)
}