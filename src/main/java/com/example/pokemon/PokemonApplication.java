package com.example.pokemon;

import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PokemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);
    }

}
