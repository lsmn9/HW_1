package geekbrains.ru.model.data

import geekbrains.ru.model.data.dto.SearchResultDto
import geekbrains.ru.model.data.userdata.DataModel
import geekbrains.ru.model.data.userdata.Meanings
import geekbrains.ru.model.data.userdata.Translation

sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}

fun mapSearchResultToResult(searchResults: List<SearchResultDto>): List<DataModel> {
    return searchResults.map { searchResult ->
        var meanings: List<Meanings> = listOf()
        searchResult.meanings?.let {
            meanings = it.map { meaningsDto ->
                Meanings(
                    Translation(meaningsDto?.translation?.translation ?: ""),
                    meaningsDto?.imageUrl ?: "",
                    meaningsDto?.transcription?:""
                )
            }
        }
        DataModel(searchResult.text ?: "", meanings)
    }
}