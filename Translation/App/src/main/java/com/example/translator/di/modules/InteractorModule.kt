package com.example.translator.di.modules

import com.example.translator.di.NAME_LOCAL
import com.example.translator.di.NAME_REMOTE
import com.example.translator.model.data.DataModel
import com.example.translator.model.repository.Repository
import com.example.translator.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}