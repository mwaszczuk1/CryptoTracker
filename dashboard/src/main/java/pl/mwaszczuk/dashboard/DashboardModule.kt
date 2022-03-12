package pl.mwaszczuk.dashboard

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import pl.mwaszczuk.dashboard.model.CryptocurrencyMapper

@InstallIn(ViewModelComponent::class)
@Module
object DashboardModule {

    @Provides
    fun provideCryptocurrencyMapper(): CryptocurrencyMapper {
        return CryptocurrencyMapper()
    }
}
