package com.example.pokemon.internal.mapper;

import com.example.pokemon.PokemonResponse;
import com.example.pokemon.PokemonResponses;
import com.example.pokemon.internal.entity.Pokemon;
import com.example.pokemon.internal.payload.PokemonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PokemonMapper {

    public PokemonDTO toDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getPokemonId(),
                pokemon.getName(),
                pokemon.isActive()
        );
    }

    public PokemonResponse toPokemonResponse(PokemonDTO dto) {

        /*PokemonResponse pokemonResponse = PokemonResponse
                .newBuilder()
                .setId(dto.getId())
                .setName(dto.getName())
                .setIsActive(dto.isActive())
                .build();*/

        return PokemonResponse
                .newBuilder()
                .setId(dto.getId())
                .setName(dto.getName())
                .setIsActive(dto.isActive())
                .build();
    }

    public Pokemon toEntity(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(pokemon.getPokemonId());
        pokemon.setName(pokemonDTO.getName());
        pokemon.setActive(pokemon.isActive());
        return pokemon;
    }

    public Pokemon update(Pokemon pokemon, PokemonDTO dto) {
        pokemon.setName(dto.getName());
        pokemon.setActive(dto.isActive());
        return pokemon;
    }

}
