package com.matischowyuen.homegymapp.network

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.matischowyuen.homegymapp.model.ExerciseBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
object ExerciseRepository {

    private const val BASE_URL = "https://gym-and-home-exercises.p.rapidapi.com/"
    private const val API_KEY = "YourApiKey" // put your api key here
    private const val HOST = "gym-and-home-exercises.p.rapidapi.com"

    private val client = OkHttpClient()
    private val gson = Gson()

    suspend fun loadExercises(muscle: String): List<ExerciseBean> = withContext(Dispatchers.IO) {
        try {
            val url = "$BASE_URL${Uri.encode(muscle)}.json"

            val request = Request.Builder()
                .url(url)
                .addHeader("X-RapidAPI-Key", API_KEY)
                .addHeader("X-RapidAPI-Host", HOST)
                .build()

            client.newCall(request).execute().use { response ->
                println("URL appelée : $url")
                println("Réponse HTTP : ${response.code}")

                val bodyString = response.body?.string()
                println("Réponse JSON : $bodyString")

                if (!response.isSuccessful || bodyString == null) {
                    return@withContext emptyList()
                }

                val jsonObject = gson.fromJson(bodyString, JsonObject::class.java)
                val contentArray = jsonObject.getAsJsonArray("content")

                val type = object : TypeToken<List<ExerciseBean>>() {}.type
                return@withContext gson.fromJson(contentArray, type)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext emptyList()
        }
    }
}
