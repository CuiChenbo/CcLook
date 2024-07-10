package com.cuichen.common.bean

class ResultBean<T> {
    var data: T? = null
    var errorCode = -1
    var errorMsg: String? = null
}