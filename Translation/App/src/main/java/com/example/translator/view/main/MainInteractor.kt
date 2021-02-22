package com.example.translator.view.main

import com.example.translator.di.NAME_LOCAL
import com.example.translator.di.NAME_REMOTE
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.repository.Repository
import com.example.translator.viewmodel.Interactor
import javax.inject.Named

class MainInteractor (
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
        if (fromRemoteSource) repositoryRemote
        else {repositoryLocal}.getData(word))
        }
}