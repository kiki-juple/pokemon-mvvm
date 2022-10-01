package com.kiki.pokemon.favorite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.kiki.core.domain.usecase.PokemonUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class FavoriteViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val useCase = mockk<PokemonUseCase>(relaxed = true)
    private val viewModel = mockk<FavoriteViewModel>(relaxed = true)

    @Test
    fun `Test Get Favorite Pokemon From UseCase`() {
        val favoritePokemon = useCase.getFavoritePokemon().asLiveData()
        every { viewModel.favoritePokemon } returns favoritePokemon
        verify { useCase.getFavoritePokemon().asLiveData() }
        assertEquals(favoritePokemon, viewModel.favoritePokemon)
    }
}