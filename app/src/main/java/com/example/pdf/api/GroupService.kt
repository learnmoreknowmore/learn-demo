package com.example.pdf.api

import com.example.pdf.bean.GroupResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GroupService {
    @GET("ecosphere/getCircleInfoTab")
    suspend fun getTabData(@QueryMap map: MutableMap<String, Any>): GroupResult<GroupResult.BasePageList<GroupResult.GroupBean>>
}