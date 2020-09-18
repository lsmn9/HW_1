package com.example.kotlin.data.model

<<<<<<< HEAD
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Note(val id: String,
                val title: String,
                val note: String,
                val color: Color = Color.WHITE,
                val lastChanged: Date = Date()): Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

enum class Color {
    WHITE,
    YELLOW,
    GREEN,
    BLUE,
    RED,
    VIOLET,
    PINK
}
=======
class Note(val title: String, val note: String, val color: Int)
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
