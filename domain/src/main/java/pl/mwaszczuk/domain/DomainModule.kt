package pl.mwaszczuk.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideDispatchers(): Dispatchers {
        return Dispatchers()
    }
}
