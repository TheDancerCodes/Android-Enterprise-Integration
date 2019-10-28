package com.example.thedancercodes.myenterpriseapplication;

import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String welcomeMessage= "Welcome to the cat bonanza!";
    boolean isImageModeEnabled = true;

    Button imageModeButton;

    Intent startImageActivityIntent;

    RestrictionsManager restrictionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startImageActivityIntent = new Intent(this,ImageActivity.class);

        Button welcomeButton = (Button) findViewById(R.id.welcomeButton);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, welcomeMessage, Snackbar.LENGTH_LONG).show();
            }
        });

        imageModeButton = (Button) findViewById(R.id.startImageModeButton);
        imageModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(startImageActivityIntent);
            }
        });

        // Define RestrictionsManager Object
        restrictionsManager = (RestrictionsManager) getSystemService(Context.RESTRICTIONS_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Google warns us that the getApplicationRestrictions method is costly to run,
         * and should be called as sparingly as possible.
         *
         * It is advised, to cache this configuration after starting or resuming and
         * listening for future changes.
         */

        Bundle applicationRestrictions = restrictionsManager.getApplicationRestrictions();

        // Checking that the Bundle has content.
        // If it has content, we can start pulling values from it.
        if (applicationRestrictions.size() > 0) {
            welcomeMessage = applicationRestrictions.getString("welcomeButtonMessage");
            isImageModeEnabled = applicationRestrictions.getBoolean("isImageModeEnabled");
        }

        imageModeButton.setEnabled(isImageModeEnabled);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
