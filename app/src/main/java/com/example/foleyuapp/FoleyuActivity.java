package com.example.foleyuapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import com.example.foleyuapp.databinding.ActivityFoleyuBinding;

public class FoleyuActivity extends AppCompatActivity  {
    private ActivityFoleyuBinding binding;
    private AudioManager audioManager;
    private SoundCategory soundCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFoleyuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        audioManager = new AudioManager(this);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        System.out.println("category: " + category);

        soundCategory = SoundCategory.valueOf(category.toLowerCase());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int width = binding.imageView.getWidth();
        int height = binding.imageView.getHeight();

        Log.i("FoleyActivity", String.format("%d %d", width, height));

        // use raw touch-down event locations to control which sound is played
        String action;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "started";

                if (x < width / 2.0f && y < height / 2.0f) {
                    // top left
                    Log.i("FoleyActivity", "top left");

                    audioManager.play(soundCategory, Position.TOP_LEFT);

                } else if (x >= width / 2.0f && y < height / 2.0f) {
                    // top right
                    Log.i("FoleyActivity", "top right");
                    audioManager.play(soundCategory, Position.TOP_RIGHT);

                } else if (x < width / 2.0f && y >= height / 2.0f) {
                    // bottom left
                    Log.i("FoleyActivity", "bottom left");
                    audioManager.play(soundCategory, Position.BOTTOM_LEFT);

                } else if (x >= width / 2.0f && y >= height / 2.0f) {
                    // bottom right
                    Log.i("FoleyActivity", "bottom right");
                    audioManager.play(soundCategory, Position.BOTTOM_RIGHT);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                action = "moved";
                break;
            case MotionEvent.ACTION_UP:
                action = "ended";
                break;
            default:
                action = "other";
        }

        String message = String.format(Locale.getDefault(), "%.2f %.2f %s", x, y, action);
        Log.i("FoleyActivity", message);

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        audioManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        audioManager.resume();
    }
}
