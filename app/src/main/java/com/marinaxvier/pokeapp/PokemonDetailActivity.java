package com.marinaxvier.pokeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marinaxvier.pokeapp.model.Pokemon;
import com.marinaxvier.pokeapp.network.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView pokemonDetailImage;
    private TextView pokemonDetailName;
    private TextView  pokemonDetailType;
    private TextView  pokemonDetailHeight;
    private TextView  pokemonDetailWeight;
    private TextView  pokemonDetailAbilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        initViews();

        Intent receivedId = getIntent();
        if(receivedId.hasExtra("pokeid")){
            int idPoke = receivedId.getIntExtra("pokeid", 0);
            loadPokemonDetail(idPoke);
        }


    }

    private void initViews() {
        pokemonDetailImage = findViewById(R.id.ivPokemonDetailImage);
        pokemonDetailName = findViewById(R.id.tvPokemonDetailName);
        pokemonDetailType = findViewById(R.id.tvPokemonDetailType);
        pokemonDetailHeight = findViewById(R.id.tvPokemonDetailHeighValue);
        pokemonDetailWeight = findViewById(R.id.tvPokemonDetailWeightValue);
        pokemonDetailAbilities = findViewById(R.id.tvPokemonDetailAbilitiesValue);
    }

    private void loadPokemonDetail(int id){
        Call<Pokemon> call = new Retrofit().getService().getPokemonDetail(id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()){
                    Pokemon pokemon = response.body();
                    inflatePokemonDetail(pokemon);
                }else{
                    Log.i("Fail", "Response error message: " +response.message());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.i("Fail", "Call error message: " +t.getMessage());
            }
        });
    }

    private void inflatePokemonDetail(Pokemon pokemon){
        pokemonDetailName.setText(pokemon.getName());
        pokemonDetailType.setText(pokemon.getPokemonTypes());
        pokemonDetailHeight.setText(String.valueOf(pokemon.getHeight() + " dm"));
        pokemonDetailWeight.setText(String.valueOf(pokemon.getWeight()+" hg"));
        pokemonDetailAbilities.setText(pokemon.getPokemonAbilities());
        loadPokemonDetailImage(pokemon.getId(), pokemonDetailImage);


    }

    public void loadPokemonDetailImage(int id, ImageView image){
        Glide.with(PokemonDetailActivity.this)
                .load( "https://pokeres.bastionbot.org/images/pokemon/"+id+".png")
                .fitCenter()
                .into(image);
    }
}