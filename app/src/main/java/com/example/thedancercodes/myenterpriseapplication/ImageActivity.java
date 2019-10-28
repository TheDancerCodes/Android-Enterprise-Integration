package com.example.thedancercodes.myenterpriseapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    // Create BroadcastReceiver
    BroadcastReceiver restrictionsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        fullScreenImage = findViewById(R.id.fullScreenImage);

        myRestrictionsManager = (RestrictionsManager) getSystemService(Context.RESTRICTIONS_SERVICE);

        // Define IntentFilter
        IntentFilter restrictionsFilter = new IntentFilter(Intent.ACTION_APPLICATION_RESTRICTIONS_CHANGED);

        // Define the BroadcastReceiver
        restrictionsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // Check the app restrictions.
                Bundle applicationRestrictions = myRestrictionsManager.getApplicationRestrictions();

                // Check that the Bundle has content
                if (applicationRestrictions.size() > 0) {

                    // Set new image URL
                    imageUrl = applicationRestrictions.getString("imageUrl");

                    // Checking that the URL is valid
                    if (URLUtil.isValidUrl(imageUrl)) {

                        // Make sure Image update call is done on the our UI thread.
                        // Let's move this into a new runnable that we post to our image.
                        fullScreenImage.post(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(getApplicationContext())
                                        .load(imageUrl)
                                        .centerCrop()
                                        .into(fullScreenImage);

                            }
                        });
                    }
                }
            }
        };
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

        // Now when this activity starts, it will attempt to startLockTask mode.
        // If a mobile device management system hasn't whitelisted this application,
        // then it will pin the activity instead.
        startLockTask();
    }

    @Override
    protected void onDestroy() {

        // Unregister the BroadcastReceiver
        unregisterReceiver(restrictionsReceiver);

        super.onDestroy();
    }

    // Although Lock Task Mode disables the status bar, home, and overview buttons,
    // it does not disable the back button.
    // For this reason, we should make sure we handle Back button presses in our locked activities.

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {

        /*
           We delete the call to super so that this button no longer does anything.
           This is because we do not want to allow our users to go back to the main activity
           as that is not configured for Lock Task Mode.
        */
        // super.onBackPressed();
    }
}
