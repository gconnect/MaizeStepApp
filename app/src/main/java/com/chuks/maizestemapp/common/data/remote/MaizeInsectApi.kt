package com.chuks.maizestemapp.common.data.remote

import com.chuks.maizestemapp.common.data.InsectResponse
import com.chuks.maizestemapp.common.data.MaizePlotResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * This setup the Maize api and makes the necessary request
 **/
interface MaizeInsectApi {

    @GET("5e6446133400005a003387ef")
    suspend fun getAllCapturedInsect() : Response<InsectResponse>

    @GET("5e6446133400005a003387ef")
    suspend fun getAfricanArmyWorm() : InsectResponse

    @GET("5e6446133400005a003387ef")
    suspend fun getEgyptianArmyWorm() : InsectResponse

    @GET("5e6446133400005a003387ef")
    suspend fun getFallArmyArmyWorm() : InsectResponse

    @GET("5e4887273000005800294b24")
    suspend fun getInsectPlot() : MaizePlotResponse
}