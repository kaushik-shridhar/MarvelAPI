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

import com.example.marvelapidemo.ComicActivity;
import com.example.marvelapidemo.ItemClasses.ComicItem;
import com.example.marvelapidemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {
    List<ComicItem> itemList;

    public ComicAdapter(List<ComicItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ComicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComicAdapter.ViewHolder holder, int position) {
        final ComicItem item = itemList.get(position);
        Picasso.get().load(item.getComicImg()).into(holder.comicImg);
        holder.comicText.setText(item.getComicText());
        holder.comicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ComicActivity.class);

                intent.putExtra("COMIC_IMAGE", item.getComicImg());
                intent.putExtra("COMIC_TITLE", item.getComicText());
                intent.putExtra("COMIC_PRICE", item.getComicPrice() + "");
                intent.putExtra("COMIC_SUMMARY", item.getComicSummary());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView comicImg;
        public TextView comicText;
        public CardView comicLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            comicImg = itemView.findViewById(R.id.comicImg);
            comicText = itemView.findViewById(R.id.comicText);
            comicLayout = itemView.findViewById(R.id.comicLayout);
        }
    }
}
