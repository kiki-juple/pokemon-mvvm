package com.kiki.pokemon.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.kiki.core.data.PokemonRepository
import com.kiki.core.domain.model.Pokemon
import com.kiki.core.domain.usecase.PokemonUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val repository = mockk<PokemonRepository>(relaxed = true)
    private val useCase = mockk<PokemonUseCase>(relaxed = true)
    private val viewModel = mockk<DetailViewModel>(relaxed = true)

    @Test
    fun `Test Get Pokemon Detail by Name`() {
        val pokemonDetail = useCase.getDetailPokemon(dummyName).asLiveData()
        every { viewModel.getPokemonDetail(dummyName) } returns pokemonDetail
        verify { useCase.getDetailPokemon(dummyName).asLiveData() }
        assertEquals(pokemonDetail, viewModel.getPokemonDetail(dummyName))
    }

    @Test
    fun `Test Set Favorite Pokemon`() {
        val favoritePokemon = useCase.setFavoritePokemon(dummyPokemon, true)
        every { viewModel.setFavoritePokemon(dummyPokemon, true) } returns favoritePokemon
        repository.setFavoritePokemon(dummyPokemon, true)
        verify { repository.setFavoritePokemon(dummyPokemon, true) }
        assertEquals(favoritePokemon, viewModel.setFavoritePokemon(dummyPokemon, true))
    }

    companion object {
        private const val dummyName = "bulbasaur"
        val dummyPokemon = Pokemon(1, dummyName, false)
    }
}