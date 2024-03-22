package com.cuichen.common.bean.user
 
 data class UserInfoBean(
    var admin: Boolean? = false,
    var chapterTops: List<Any?>? = listOf(),
    var coinCount: Int? = 0,
    var collectIds: List<Any?>? = listOf(),
    var email: String? = "",
    var icon: String? = "",
    var id: Int? = 0,
    var nickname: String? = "",
    var password: String? = "",
    var publicName: String? = "",
    var token: String? = "",
    var type: Int? = 0,
    var username: String? = ""
)