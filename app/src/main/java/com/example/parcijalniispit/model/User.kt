package com.example.parcijalniispit.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val detalj: String,
    val naziv: String,
): Parcelable