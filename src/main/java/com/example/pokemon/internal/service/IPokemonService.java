package com.example.pokemon.internal.service;

import com.example.pokemon.exception.MainError;
import com.example.pokemon.exception.MainException;
import com.example.pokemon.internal.entity.Pokemon;
import com.example.pokemon.internal.mapper.PokemonMapper;
import com.example.pokemon.internal.repository.PokemonRepository;
import com.example.pokemon.internal.payload.PokemonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IPokemonService implements PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonMapper mapper;

    @Override
    public PokemonDTO getPokemon(Long pokemonId) {
        if (!pokemonRepository.existsById(pokemonId))
            throw new NoSuchElementException("No such element: " + pokemonId);
        return mapper.toDTO(getEntity(pokemonId));
    }

    @Override
    public Pokemon getEntity(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(
                        () -> MainException.of(
                                MainError.NOT_FOUND_ERROR,
                                "Pokemon not found by id: " + id
                        )
                );
    }

    @Override
    public List<PokemonDTO> getAllPokemons() {
        return pokemonRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePokemon(Long id) {
        if (!pokemonRepository.existsById(id))
            throw MainException.of(MainError.NOT_FOUND_ERROR, "No such element");
        //Pokemon pokemon = pokemonRepository.getReferenceById(pokemonId); // через getEntity будет норм?

        pokemonRepository.delete(getEntity(id));
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO dto) {
        return mapper.toDTO(
                pokemonRepository.save(
                        new Pokemon(
                                null, // если здесь null он сам будет всё равно генирть idшники?
                                dto.getName(),
                                dto.isActive()
                        )
                )
        );
    }

    @Override
    public PokemonDTO updateById(
            Long id,
            PokemonDTO dto
    ) {
        if (!pokemonRepository.existsById(id))
            throw MainException.of(MainError.NOT_FOUND_ERROR, "No such element");

        return mapper.toDTO(
                pokemonRepository.save(
                        mapper.update(
                                getEntity(id),
                                dto
                        )
                )
        );
    }

    @Override
    public String hello(String helloRequest) {
        return "Hello mr " + helloRequest;
    }

}
