package com.marinaxvier.pokeapp.model;

import java.util.List;

public class Pokemon {

    private int id;
    private String name;
    private List<Types> types;
    private int height;
    public int weight;
    private List<Abilities> abilities;
    private String pokemonAbilities = "";
    private String pokemonTypes = "";

    public String getPokemonTypes() {
//
        if(types != null){
            return types.get(0).getType().getName();
        }

        return pokemonTypes;
    }

    public void setPokemonTypes(String pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public String getPokemonAbilities() {

        for (Abilities a: getAbilities()) {
            pokemonAbilities += a.getAbility().getName() + "\n";
        }

        return pokemonAbilities;
    }

    public void setPokemonAbilities(String pokemonAbilities) {
        this.pokemonAbilities = pokemonAbilities;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
