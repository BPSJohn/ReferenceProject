package com.example.android.simpsonfortniteproject.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FortniteObject (
    val data: List<FortniteCosmetic>
): Parcelable

@Parcelize
data class FortniteCosmetic(
    val id: String,
    val name: String,
    val description: String,
    val rarity: ItemRarity,
    val images: ItemImage
): Parcelable

@Parcelize
data class ItemRarity(
    val displayValue: String
): Parcelable

@Parcelize
data class ItemImage(
    val icon: String
): Parcelable
