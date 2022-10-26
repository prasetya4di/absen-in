package com.pras.absenin.view.history_detail;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pras.absenin.databinding.ActivityHistoryDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryDetailActivity extends AppCompatActivity {
    private ActivityHistoryDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
