package com.pw.pwrickapp.di

import com.pw.pwrickapp.repository.CharacterDataRepository
import com.pw.pwrickapp.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ritik on: 31/08/24
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserAccountDataRepository(): CharacterDataRepository = CharacterRepository()


}