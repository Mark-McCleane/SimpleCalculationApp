package com.example.aerlingusassessment.data

import com.example.aerlingusassessment.domain.usecases.PerformCalculationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePerformCalculationUseCase(): PerformCalculationUseCase {
        return PerformCalculationUseCase()
    }
}