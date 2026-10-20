package is.xyz.mpv

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface SubsourceApi {
    @GET("api/v1/subtitles")
    suspend fun searchSubtitles(
        @Header("apikey") apiKey: String, // Menyertakan API Key Subsource di header
        @Query("query") query: String,
        @Query("lang") lang: String = "en"
    ): Response<List<SubtitleItem>>

    @GET
    suspend fun downloadFile(
        @Header("apikey") apiKey: String, // Menyertakan API Key saat mengunduh file
        @Url fileUrl: String
    ): Response<ResponseBody>

    companion object {
        private const val BASE_URL = "https://api.subsource.net/" // Base URL resmi Subsource
        const val MY_API_KEY = "sk_1380ad59dd6ac78e1e7566bdd7b75d3820a8a42dd1da7e744ccf649c30dba770"   // <--- Ganti dengan API Key Anda

        fun create(): SubsourceApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SubsourceApi::class.java)
        }
    }
}
