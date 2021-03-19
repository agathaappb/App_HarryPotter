package com.agathaappb.harrypotter


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type


class HarryRepository {
    fun loadData () : java.util.ArrayList<Harry>? {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://hp-api.herokuapp.com/api/characters")
            .build()
        //Tudo pronto! Hora de realizar a chamada! *-*
        val response = client.newCall(request).execute()
        val result = parseJson(response.body?.string())
        return result
    }
    //Beleza, vamos converter o Json! \(*-*)/
    fun parseJson(json: String?): java.util.ArrayList<Harry>? {
//        var array = ArrayList<Harry>()
//        var resultado = Gson().fromJson(json, arrayListOf<Harry>()::class.java)
//        return resultado.toList()
        val gson = Gson()
        val listOfMyClassObject: Type = object : TypeToken<ArrayList<Harry?>?>() {}.type
        val outputList: java.util.ArrayList<Harry>? = gson.fromJson(json, listOfMyClassObject)
        return outputList
    }
}