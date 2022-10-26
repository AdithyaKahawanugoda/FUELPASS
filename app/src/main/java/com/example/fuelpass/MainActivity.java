package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class MainActivity implements the splash screen of the app
 */
public class MainActivity extends AppCompatActivity {

    private static final int STARTUP_SCREEN = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide status bar from the starting activity window
        getWindow().getDecorView().getWindowInsetsController().hide(
                android.view.WindowInsets.Type.statusBars()
                        | android.view.WindowInsets.Type.navigationBars()
        );

        // startup image animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //  ui element hooks
        ImageView appLogo = findViewById(R.id.startup_logo);

        // assign Animation
        appLogo.setAnimation(fadeIn);

        // Create an executor that executes tasks in a background thread.
        ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();

        // Execute a task in the background thread after 3 seconds.
        backgroundExecutor.schedule(() -> {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        }, STARTUP_SCREEN, TimeUnit.SECONDS);

    }
}