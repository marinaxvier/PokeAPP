package com.marinaxvier.pokeapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.marinaxvier.pokeapp.R;
import com.marinaxvier.pokeapp.model.Pokemon;
import com.marinaxvier.pokeapp.network.Retrofit;
import com.marinaxvier.pokeapp.util.StringUtil;

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
    private ProgressBar progressBar;
    private ImageView ivArrowBackPokemonDetail;


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

        ivArrowBackPokemonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void initViews() {
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        pokemonDetailImage = findViewById(R.id.ivPokemonDetailImage);
        pokemonDetailName = findViewById(R.id.tvPokemonDetailName);
        pokemonDetailType = findViewById(R.id.tvPokemonDetailType);
        pokemonDetailHeight = findViewById(R.id.tvPokemonDetailHeighValue);
        pokemonDetailWeight = findViewById(R.id.tvPokemonDetailWeightValue);
        pokemonDetailAbilities = findViewById(R.id.tvPokemonDetailAbilitiesValue);
        ivArrowBackPokemonDetail = findViewById(R.id.ivArrowBack);
        progressBar.setVisibility(View.VISIBLE);
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
        pokemonDetailName.setText(StringUtil.toProperCase(pokemon.getName()));
        pokemonDetailType.setText(StringUtil.toProperCase(pokemon.getPokemonTypes()));
        pokemonDetailHeight.setText(String.valueOf(pokemon.getHeight() + " dm"));
        pokemonDetailWeight.setText(String.valueOf(pokemon.getWeight()+" hg"));
        pokemonDetailAbilities.setText(pokemon.getPokemonAbilities());
        loadPokemonDetailImage(pokemon.getId(), pokemonDetailImage);
        progressBar.setVisibility(View.GONE);


    }

    public void loadPokemonDetailImage(int id, ImageView image){
        Glide.with(PokemonDetailActivity.this)
                .load( "https://pokeres.bastionbot.org/images/pokemon/"+id+".png")
                .fitCenter()
                .apply(new RequestOptions().placeholder(R.drawable.animation))
                .into(image);
    }
}