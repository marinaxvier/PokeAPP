package com.marinaxvier.pokeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonReturn {
    private int count;
    private String next;
    private String previous;
    @SerializedName("results")
    private List<PokemonList> pokemonList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokemonList> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<PokemonList> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
