package com.marinaxvier.pokeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.marinaxvier.pokeapp.adapter.PokemonListAdapter;
import com.marinaxvier.pokeapp.model.PokemonList;
import com.marinaxvier.pokeapp.model.PokemonReturn;
import com.marinaxvier.pokeapp.network.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PokemonListAdapter adapter;
    private List<PokemonList> pokemonList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        loadData();

        recyclerView = findViewById(R.id.rvPokemonList);
        adapter = new PokemonListAdapter(pokemonList, this, new OnItemClickListener() {
            @Override
            public void onItemClick(PokemonList pokemon, int position) {
                Intent startPokemonDetailPage = new Intent(PokemonListActivity.this, PokemonDetailActivity.class);
                startPokemonDetailPage.putExtra("pokeid", pokemon.getId());
                startActivity(startPokemonDetailPage);
            }
        });
                recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);



    }

    private void loadData() {
        Call<PokemonReturn> call = new Retrofit().getService().getPokemon();
        call.enqueue(new Callback<PokemonReturn>() {

            @Override
            public void onResponse(Call<PokemonReturn> call, Response<PokemonReturn> response) {
                Log.i("Sucesso", "onResponse: " + response);
                PokemonReturn pokemonReturn = response.body();
                adapter.updateList(pokemonReturn.getPokemonList());
            }

            @Override
            public void onFailure(Call<PokemonReturn> call, Throwable t) {
                Log.i("Falha", "onFailure: " + t.getMessage());
            }
        });
    }
}