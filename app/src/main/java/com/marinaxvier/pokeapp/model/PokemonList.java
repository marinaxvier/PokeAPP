package com.marinaxvier.pokeapp.model;

public class PokemonList {
    private String name;
    private String url;
    private int id;
    private boolean isFavorited = false;

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public int getId() {
        String[] idPoke = getUrl().split("/");
        return Integer.parseInt(idPoke[idPoke.length-1]);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
