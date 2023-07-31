package com.example.pokemon.internal.service;

import com.example.pokemon.internal.entity.Pokemon;
import com.example.pokemon.internal.payload.PokemonDTO;

import java.util.List;

public interface PokemonService {
    PokemonDTO getPokemon(Long pokemonId);
    Pokemon getEntity(Long id);
    List<PokemonDTO> getAllPokemons();
    void deletePokemon(Long pokemonId);
    PokemonDTO createPokemon(PokemonDTO dto);
    PokemonDTO  updateById(Long id, PokemonDTO dto);
    String hello(String helloResponse);

}
