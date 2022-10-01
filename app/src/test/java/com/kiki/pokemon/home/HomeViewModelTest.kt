package com.kiki.pokemon.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.kiki.core.domain.usecase.PokemonUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val useCase = mockk<PokemonUseCase>(relaxed = true)
    private val viewModel = mockk<HomeViewModel>(relaxed = true)

    @Test
    fun `Test Get All Pokemon From UseCase`() {
        val pokemon = useCase.getAllPokemon().asLiveData()
        every { viewModel.pokemon } returns pokemon
        verify { useCase.getAllPokemon().asLiveData() }
        assertEquals(pokemon, viewModel.pokemon)
    }
}