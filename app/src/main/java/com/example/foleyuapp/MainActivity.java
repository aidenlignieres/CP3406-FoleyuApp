package com.example.foleyuapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foleyuapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final View.OnClickListener clickListener = view -> {
        Button button = (Button) view;

        Intent intent = new Intent(MainActivity.this, FoleyuActivity.class);
        intent.putExtra("category", button.getText().toString());
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.animal.setOnClickListener(clickListener);
        binding.nature.setOnClickListener(clickListener);
        binding.human.setOnClickListener(clickListener);
        binding.technology.setOnClickListener(clickListener);
    }
}