package com.marinaxvier.pokeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marinaxvier.pokeapp.OnItemClickListener;
import com.marinaxvier.pokeapp.R;
import com.marinaxvier.pokeapp.model.PokemonList;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<PokemonList> pokemonList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context context;



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PokemonListAdapter(List<PokemonList> pokemonList, Context context, OnItemClickListener onItemClickListener) {
        this.pokemonList = pokemonList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    public void loadImage(int id, ImageView image){
        Glide.with(context)
                .load( "https://pokeres.bastionbot.org/images/pokemon/"+id+".png")
                .centerCrop()
                .into(image);

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pokemon_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PokemonList pokemon = pokemonList.get(position);
        holder.bind(pokemon, onItemClickListener);
        String finalName = pokemon.getName().substring(0,1).toUpperCase() + pokemon.getName().substring(1);

        holder.tvPokemonName.setText(finalName);
        loadImage(pokemon.getId(),holder.ivPokemonImage);


//        holder.favoritePokemon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (pokemon.isFavorited()){
//                    holder.favoritePokemon.setImageResource(R.drawable.ic_star_border);
//                    pokemon.setFavorited(false);
//                } else{
//                    holder.favoritePokemon.setImageResource(R.drawable.ic_star_filled);
//                    pokemon.setFavorited(true);
//                }
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void updateList(List<PokemonList> list){
        pokemonList.clear();
        pokemonList.addAll(list);
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvPokemonName;
    ImageView ivPokemonImage;
    ImageView favoritePokemon;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvPokemonName = itemView.findViewById(R.id.tvPokemonItemListName);
        ivPokemonImage = itemView.findViewById(R.id.ivPokemonItemListImage);
//        favoritePokemon = itemView.findViewById(R.id.ivPokemonItemListFavorite);

    }

    public void bind(final PokemonList pokemonList, final OnItemClickListener onItemClickListener){
    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(pokemonList, getAdapterPosition());
        }
    });
    }

}
