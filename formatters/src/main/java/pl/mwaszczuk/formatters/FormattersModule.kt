package pl.mwaszczuk.formatters

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object FormattersModule {

    @Provides
    fun provideBigNumberFormatter(): BigNumberFormatter {
        return BigNumberFormatter()
    }
}
