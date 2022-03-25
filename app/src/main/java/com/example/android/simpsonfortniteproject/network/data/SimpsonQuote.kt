package com.example.android.simpsonfortniteproject.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimpsonQuote(
    val quote: String,
    val character: String,
    val image: String,
    val characterDirection: String
): Parcelable
