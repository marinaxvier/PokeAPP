package com.marinaxvier.pokeapp.network;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private String BASEURL = "https://pokeapi.co/api/v2/";
    private retrofit2.Retrofit retrofit;

    public Retrofit(){
        this.retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public PokeapiService getService(){
        return retrofit.create(PokeapiService.class);
    }
}
