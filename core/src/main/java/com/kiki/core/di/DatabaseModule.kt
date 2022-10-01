package com.kiki.core.di

import android.content.Context
import androidx.room.Room
import com.kiki.core.data.source.local.room.PokemonDao
import com.kiki.core.data.source.local.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase = SQLiteDatabase.getBytes("pokemon".toCharArray())
    private val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "Pokemon.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()


    @Provides
    fun providePokemonDao(database: PokemonDatabase): PokemonDao = database.pokemonDao()
}