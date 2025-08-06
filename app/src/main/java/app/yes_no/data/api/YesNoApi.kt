package app.yes_no.data.api

import app.yes_no.data.model.YesNoDto
import retrofit2.http.GET

interface YesNoApi {

    @GET("api")
    suspend fun getYesNo(): YesNoDto


}