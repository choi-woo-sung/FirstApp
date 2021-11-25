package com.example.firstapp.model.Champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * FirstApp
 * Class: Champion
 * Created by 82102 on 2021-11-25.
 *
 * Description:
 */
@Parcelize
data class Champion (
//    val type: Type,
//    val format: String,
//    val version: Version,
    val data: Map<String, Datum>
) : Parcelable



