package com.pras.absenin.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pras.absenin.databinding.ActivityMainBinding;
import com.pras.absenin.view.absen.AbsenActivity;
import com.pras.absenin.view.history.HistoryActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAbsen.setOnClickListener(v -> startActivity(new Intent(this, AbsenActivity.class)));

        binding.btnHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));

        binding.btnExit.setOnClickListener(v -> finishAndRemoveTask());
    }
}
