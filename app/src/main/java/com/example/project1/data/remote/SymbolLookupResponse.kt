package com.example.project1.data.remote

import com.google.gson.annotations.SerializedName

data class SymbolLookupResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("result")
    val result: List<Lookup>,
)