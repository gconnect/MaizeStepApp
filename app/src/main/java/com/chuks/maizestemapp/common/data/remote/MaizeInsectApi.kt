package com.chuks.maizestemapp.common.data.remote

import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.InsectModel
import com.chuks.maizestemapp.common.data.InsectResponse
import com.chuks.maizestemapp.common.data.MaizePlotResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * This setup the Maize api and makes the necessary request
 **/
interface MaizeInsectApi {

//    @GET("5e6446133400005a003387ef")
    @GET("dev/mothPredictionAPI?ClassName=ALL")
    suspend fun getAllCapturedInsect() : Response<List<Insect>>

//    @GET("5e6446133400005a003387ef")
     @GET("dev/mothPredictionAPI/aaw?ClassName=AAW")
      fun getAfricanArmyWorm() : Response<List<Insect>>

//    @GET("5e6446133400005a003387ef")
    @GET("dev/mothPredictionAPI/eclw?ClassName=ECLW")
    suspend fun getEgyptianArmyWorm() : Response<List<Insect>>

//    @GET("5e6446133400005a003387ef")
    @GET("dev/mothPredictionAPI/faw?ClassName=FAW")
    suspend fun getFallArmyArmyWorm() : Response<List<Insect>>

    @GET("5e4887273000005800294b24")
    suspend fun getInsectPlot() : MaizePlotResponse
}