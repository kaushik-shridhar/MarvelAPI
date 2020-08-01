package com.example.marvelapidemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ComicActivity extends AppCompatActivity {
    private ImageView comicImageView;
    private TextView comicTitleView;
    private Button priceView;
    private TextView summaryView;

    private ImageView backButton;
    private ImageView shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        comicImageView = findViewById(R.id.comicImage);
        comicTitleView = findViewById(R.id.comicTitle);
        priceView = findViewById(R.id.price);
        summaryView = findViewById(R.id.comicSummary);
        shareButton = findViewById(R.id.shareComic);
        backButton = findViewById(R.id.backButtonComic);


        String comicImage = getIntent().getStringExtra("COMIC_IMAGE");
        String comicTitle = getIntent().getStringExtra("COMIC_TITLE");
        final String price = getIntent().getStringExtra("COMIC_PRICE");
        String summary = getIntent().getStringExtra("COMIC_SUMMARY");

        Picasso.get().load(comicImage).into(comicImageView);
        comicTitleView.setText(comicTitle);
        priceView.setText("$" + price);
        summaryView.setText(summary);

        priceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComicActivity.this, "Book purchased for : $" + price, Toast.LENGTH_SHORT).show();
            }
        });

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
                startActivity(new Intent(ComicActivity.this, MainActivity.class));
                /*
                Intent intent = new Intent(ComicActivity.this, MainActivity.class);
                intent.putExtra("COMIC_REFERENCE", "comic");
                startActivity(intent);
                //getSupportFragmentManager().beginTransaction().replace(R.id.comicContainer, new ComicFragment()).commit();

                 */
            }
        });
    }
}
