package com.example.kotlinroomdbcrud.navigationComponent.CustomObjectSafeArgs

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class profileModel(
    var firstName: String,
    var lastName: String,
    var age: Int
): Parcelable
