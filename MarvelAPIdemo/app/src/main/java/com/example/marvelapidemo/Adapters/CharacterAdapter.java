package com.example.marvelapidemo.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelapidemo.CharacterActivity;
import com.example.marvelapidemo.ItemClasses.CharacterItem;
import com.example.marvelapidemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    List<CharacterItem> itemListChar;

    public CharacterAdapter(List<CharacterItem> itemListChar) {
        this.itemListChar = itemListChar;
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, final int position) {
        Picasso.get().load(itemListChar.get(position).getCharacterImage()).into(holder.characterImage);
        holder.characterName.setText(itemListChar.get(position).getCharacterName());
        holder.characterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CharacterActivity.class);
                intent.putExtra("CHARACTER_NAME", itemListChar.get(position).getCharacterName());
                intent.putExtra("CHARACTER_IMAGE", itemListChar.get(position).getCharacterImage());
                intent.putExtra("CHARACTER_DESCRIPTION", itemListChar.get(position).getCharacterDescription());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemListChar.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView characterImage;
        public TextView characterName;
        public CardView characterLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            characterImage = itemView.findViewById(R.id.characterImage);
            characterName = itemView.findViewById(R.id.characterName);
            characterLayout = itemView.findViewById(R.id.characterLayout);
        }
    }
}
