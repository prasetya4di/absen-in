package com.pras.absenin.view.history;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pras.absenin.databinding.ActivityHistoryBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryActivity extends AppCompatActivity {
    private ActivityHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
