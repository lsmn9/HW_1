package geekbrains.ru.model.data.userdata

import com.google.gson.annotations.SerializedName

class Meanings(
    val translation: Translation = Translation(),
    val imageUrl: String = "",
    val transcription: String = ""
)
