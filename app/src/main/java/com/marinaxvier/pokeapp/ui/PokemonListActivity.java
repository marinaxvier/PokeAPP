package com.marinaxvier.pokeapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.marinaxvier.pokeapp.ui.adapter.OnItemClickListener;
import com.marinaxvier.pokeapp.R;
import com.marinaxvier.pokeapp.ui.adapter.PokemonListAdapter;
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
    private int offset = 0;


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

        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void update() {
                super.update();
                offset += 20;
                loadData();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);


    }


    private void loadData() {
        Call<PokemonReturn> call = new Retrofit().getService().getPokemon(20, offset);
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