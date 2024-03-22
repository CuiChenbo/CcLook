package com.cuichen.common.bean

class ResultBean<T> {
    var data: T? = null
    var errorCode = 0
    var errorMsg: String? = null
}