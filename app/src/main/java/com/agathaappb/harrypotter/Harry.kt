package com.agathaappb.harrypotter

import com.google.gson.annotations.SerializedName
data class Harry(
    @SerializedName("name") val name : String,
    @SerializedName("house") val house : String
)