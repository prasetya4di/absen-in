package com.pras.absenin.view.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pras.absenin.databinding.ActivityHistoryBinding;
import com.pras.absenin.view.history_detail.HistoryDetailActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryActivity extends AppCompatActivity {
    private ActivityHistoryBinding binding;
    private HistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        viewModel.getAllAbsentHistory();

        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this));

        viewModel.listAbsent.observeForever(absents -> {
            HistoryAdapter historyAdapter = new HistoryAdapter(absents, absent -> {
                startActivity(new Intent(this, HistoryDetailActivity.class));
            });
            binding.rvHistory.setAdapter(historyAdapter);
        });
    }
}
