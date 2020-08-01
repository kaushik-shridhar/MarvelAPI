package com.example.marvelapidemo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.marvelapidemo.Adapters.CharacterAdapter;
import com.example.marvelapidemo.CharacterModel.Character;
import com.example.marvelapidemo.CharacterModel.CharacterDataWrapper;
import com.example.marvelapidemo.CharacterModel.Image;
import com.example.marvelapidemo.ItemClasses.CharacterItem;
import com.example.marvelapidemo.JsonPlaceHolderAPI;
import com.example.marvelapidemo.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterFragment extends Fragment {
    RecyclerView recyclerView;
    JsonPlaceHolderAPI placeHolderAPI;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    List<CharacterItem> itemListChar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);

        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemListChar = new ArrayList<CharacterItem>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        placeHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<CharacterDataWrapper> call = placeHolderAPI.loadCharacters(1, "023508966226cc2d97e756a8e39986ff", "67ef257a2ae8e8ca11cd63e9420351b0");
        call.enqueue(new Callback<CharacterDataWrapper>() {
            @Override
            public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                if (!response.isSuccessful()) {
                    //textView.setText("Code: " + response.code());
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                } else {
                    String Name = "";
                    String image = "";
                    String description = "";

                    Character[] characters = response.body().getData().getResults();

                    for (Character character : characters) {
                        Name = character.getName();
                        description = character.getDescription();

                        Image img = character.getThumbnail();
                        image = img.getPath() + "." + img.getExtension();

                        itemListChar.add(new CharacterItem(image, Name, description));
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                //textView.setText("Failure: " + t.getMessage());
            }
        });

        mAdapter = new CharacterAdapter(itemListChar);
        mRecyclerView.setAdapter(mAdapter);


        // Inflate the layout for this fragment
        return view;


    }
}


