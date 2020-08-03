package com.example.pdf.datasource

import androidx.paging.PagingSource
import com.example.pdf.api.ApiGenerate
import com.example.pdf.api.GroupService
import com.example.pdf.bean.GroupResult
import com.example.pdf.bean.News
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class ByItemDataSource : PagingSource<Int, GroupResult.GroupBean>() {
    private val service: GroupService by lazy {
        ApiGenerate.getGroupService()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GroupResult.GroupBean> {

        return try {
            var page: Int = params.key ?: 1
            val data = service.getTabData(param(page = page))
            LoadResult.Page(
                data = data.content!!.data,
                prevKey = null,
                nextKey = if (data.content!!.current_page == data.content!!.total) null else page + 1
            )

        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    private fun param(page: Int): MutableMap<String, Any> {
        val map: MutableMap<String, Any> = HashMap()
        map["type"] = 1
        map["page"] = page
        map["per_page"] = 10
        map["cid"] = 44
        map["token"] = "wj"
        map["sign"] = getSign(map, "20")
        return map
    }

    private fun getSign(
        params: Map<String, Any>,
        key: String
    ): String {
        var sign: String? = null
        val array:Array<String> = params.keys.toTypedArray()
        val sb = StringBuilder()
        var index = 0
        for (i in params) {
            Arrays.sort(array)
            sb.append(array[index]).append("=").append(params[array[index]]).append("&")
            index++
        }
        sb.append("key=").append(key)
        try {
            sign = crypt(sb.toString()).toUpperCase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sign!!
    }

    private fun crypt(str: String): String {
        require(!(str == null || str.isEmpty())) { "String to encript cannot be null or zero length" }
        val hexString = StringBuffer()
        try {
            val md = MessageDigest.getInstance("MD5")
            md.update(str.toByteArray())
            val hash = md.digest()
            for (i in hash.indices) {
                if (0xff and hash[i].toInt() < 0x10) {
                    hexString.append("0" + Integer.toHexString(0xFF and hash[i].toInt()))
                } else {
                    hexString.append(Integer.toHexString(0xFF and hash[i].toInt()))
                }
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return hexString.toString()
    }
}