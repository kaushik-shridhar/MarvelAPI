package com.example.marvelapidemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class CharacterActivity extends AppCompatActivity {
    private ImageView characterImageView;
    private TextView characterDescriptionView;
    private TextView characterNameView;

    private ImageView backButton;
    private ImageView shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        characterImageView = findViewById(R.id.characterImage);
        characterDescriptionView = findViewById(R.id.characterDescription);
        backButton = findViewById(R.id.backButtonCharacter);
        shareButton = findViewById(R.id.shareCharacter);
        characterNameView = findViewById(R.id.characterName);

        String characterName = getIntent().getStringExtra("CHARACTER_NAME");
        String characterImage = getIntent().getStringExtra("CHARACTER_IMAGE");
        String characterDescription = getIntent().getStringExtra("CHARACTER_DESCRIPTION");

        Picasso.get().load(characterImage).into(characterImageView);
        if (characterDescription.equals(null)) {
            characterDescriptionView.setText("No description available");
        } else {
            characterDescriptionView.setText(characterDescription);
        }
        characterNameView.setText(characterName);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is some data to share";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharacterActivity.this, MainActivity.class));
                /*
                Intent intent = new Intent(CharacterActivity.this, MainActivity.class);
                intent.putExtra("CHARACTER_REFERENCE", "character");
                startActivity(intent);

                 */
                //getSupportFragmentManager().beginTransaction().replace(R.id.characterContainer, new CharacterFragment()).commit();
            }
        });
    }
}
