package com.marinaxvier.pokeapp.network;

import com.marinaxvier.pokeapp.model.Pokemon;
import com.marinaxvier.pokeapp.model.PokemonList;
import com.marinaxvier.pokeapp.model.PokemonReturn;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {
    @GET("pokemon/")
    Call<PokemonReturn> getPokemon(@Query("limit") int limit, @Query("offset") int offset );

    @GET("pokemon/{id}/")
    Call<Pokemon> getPokemonDetail(@Path("id") int id);
}
