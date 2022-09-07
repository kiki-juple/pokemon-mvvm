package com.kiki.pokemon.favorite.di

import android.content.Context
import com.kiki.pokemon.di.FavoriteModule
import com.kiki.pokemon.favorite.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(favoriteDependencies: FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}