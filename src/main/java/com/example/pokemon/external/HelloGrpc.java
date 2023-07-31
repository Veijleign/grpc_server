package com.example.pokemon.external;


import com.example.pokemon.*;
import com.example.pokemon.internal.mapper.PokemonMapper;
import com.example.pokemon.internal.payload.PokemonDTO;
import com.example.pokemon.internal.service.IPokemonService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class HelloGrpc extends PokemonServiceGrpc.PokemonServiceImplBase {

    private final IPokemonService pokemonService;
    private final PokemonMapper mapper;

    public HelloGrpc(IPokemonService pokemonService, PokemonMapper mapper) {
        this.pokemonService = pokemonService;
        this.mapper = mapper;
    }

    @Override // works correctly
    public void greeting(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {

        String message = pokemonService.hello(request.getName());

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void create(CreatePokemonRequest request,
                       StreamObserver<PokemonResponse> responseObserver) {

        PokemonDTO pokemon = pokemonService.createPokemon(new PokemonDTO(
                null,
                request.getName(),
                request.getIsActive()
        ));

        PokemonResponse response = mapper.toPokemonResponse(pokemon);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(GetPokemonRequest request,
                       StreamObserver<PokemonResponse> responseObserver) {
        PokemonDTO pokemon = pokemonService.getPokemon(request.getId());

        PokemonResponse response = mapper.toPokemonResponse(pokemon);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void delete(GetPokemonRequest request,
                       StreamObserver<Empty> responseObserver) {

        pokemonService.deletePokemon(request.getId());

        Empty emptyResponse = Empty.newBuilder().build();

        responseObserver.onNext(emptyResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void update(UpdatePokemonRequest request,
                       StreamObserver<PokemonResponse> responseObserver) {

        PokemonDTO pokemon = pokemonService.updateById(
                request.getId(),
                new PokemonDTO(
                        request.getId(),
                        request.getName(),
                        request.getIsActive()
                ));

        PokemonResponse response = mapper.toPokemonResponse(pokemon);

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getAll(Empty request,
                       StreamObserver<PokemonResponses> responseObserver) {

        List<PokemonResponse> pokemonResponses = pokemonService.getAllPokemons()
                .stream()
                .map(mapper::toPokemonResponse)
                .toList();

        PokemonResponses responses = PokemonResponses
                .newBuilder()
                .addAllResponse(pokemonResponses)
                .build();

        responseObserver.onNext(responses);
        responseObserver.onCompleted();
    }
}