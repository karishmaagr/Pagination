package com.example.gojekpractice.di

import com.example.gojekpractice.domain.StarWarRepo
import com.example.gojekpractice.domain.StarrWarRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindStarWarRepo(
        characterRepositoryImpl: StarrWarRepoImpl
    ): StarWarRepo

}