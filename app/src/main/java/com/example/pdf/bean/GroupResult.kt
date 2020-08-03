package com.example.pdf.bean

data class GroupResult<T>(
    var code:Int = 0,
    var content:T? = null
) {
    data class BasePageList<T>(
        var total:Int = 0,
        var per_page:Int = 0,
        var current_page:Int = 0,
        var last_page:Int = 0,
        var data:List<T> = emptyList()
    )
    data class GroupBean(
        var id:Int = 0,
        var content:String = "",
        var uname:String = "",
        var uheadimg:String = ""
    )
}