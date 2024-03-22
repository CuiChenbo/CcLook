package com.cuichen.common.http

object okUrl {
    private const val BASEURL_ANDROID = "https://www.wanandroid.com"
    private const val BASEURL_GANK = "https://gank.io/api/v2"
    private const val BASEURL_QIUBAI = "https://m2.qiushibaike.com"
    var BANNER = BASEURL_ANDROID + "/banner/json"

    /*
   1.1 首页文章列表
    https://www.wanandroid.com/article/list/0/json
    方法：GET
    参数：页码，拼接在连接中，从0开始。
     */
    var ARTICLE_LIST = BASEURL_ANDROID + "/article/list"
    var ARTICLE_TOP = BASEURL_ANDROID + "/article/top/json" //置顶文章

    /*
    5.2 注册
    https://www.wanandroid.com/user/register
    方法：POST
    参数 username,password,repassword
     */
    var REGISTER = BASEURL_ANDROID + "/user/register"

    /*
  5.1 登录
    https://www.wanandroid.com/user/login
    方法：POST
    参数：username，password
    */
    var LOGIN = BASEURL_ANDROID + "/user/login"
    var LOGOUT = BASEURL_ANDROID + "/user/logout/json"

    /*
 收藏文章列表
https://www.wanandroid.com/lg/collect/list/0/json

方法：GET
参数： 页码：拼接在链接中，从0开始。
    */
    var COLLECT_LIST = BASEURL_ANDROID + "/lg/collect/list/0/json"

    //  BASEURL_GANK+"/data/category/Girl/type/Girl/page/1/count/10";
    var Girl = BASEURL_GANK + "/data/category/Girl/type/Girl/"
    var GIRL_BANNER = BASEURL_GANK + "/banners"

    /**
     * 糗事百科
     * http://m2.qiushibaike.com/article/list/{type}?type=refresh&page={page}&count={count}
     * 参数type为类型，latest最新、text文本、imgrank图片、video视频
     * 参数page为页码；参数count为每页数量
     * 示例：http://m2.qiushibaike.com/article/list/text?type=refresh&page=1&count=12
     */
    var QiuBaiTextUrl = BASEURL_QIUBAI + "/article/list/text"
    var QiuBaiImgUrl = BASEURL_QIUBAI + "/article/list/imgrank"
}