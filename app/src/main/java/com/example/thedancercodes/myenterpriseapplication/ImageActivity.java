package com.example.thedancercodes.myenterpriseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    String imageUrl = "http://wisdompets.com/wp-content/uploads/2015/10/cat-scratching-post-97572046-262x300.jpg";

    ImageView fullScreenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        fullScreenImage = findViewById(R.id.fullScreenImage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Glide.with(getApplicationContext())
                .load(imageUrl)
                .centerCrop()
                .into(fullScreenImage);
    }
}
