package com.example.thedancercodes.myenterpriseapplication;

import android.content.Context;
import android.content.RestrictionsManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    String imageUrl = "http://wisdompets.com/wp-content/uploads/2015/10/cat-scratching-post-97572046-262x300.jpg";

    ImageView fullScreenImage;

    // Create the Restrictions Manager object
    RestrictionsManager myRestrictionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        fullScreenImage = findViewById(R.id.fullScreenImage);

        myRestrictionsManager = (RestrictionsManager) getSystemService(Context.RESTRICTIONS_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check the app restrictions.
        Bundle applicationRestrictions = myRestrictionsManager.getApplicationRestrictions();

        // Check that the Bundle has content
        if (applicationRestrictions.size() > 0) {

            // Set new image URL
            imageUrl = applicationRestrictions.getString("imageUrl");

            // Checking that the URL is valid
            if (URLUtil.isValidUrl(imageUrl)) {

                Glide.with(getApplicationContext())
                        .load(imageUrl)
                        .centerCrop()
                        .into(fullScreenImage);
            }
        }
    }
}
