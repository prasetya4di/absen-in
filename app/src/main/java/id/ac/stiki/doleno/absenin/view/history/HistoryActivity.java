package id.ac.stiki.doleno.absenin.view.history;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.databinding.ActivityHistoryBinding;
import id.ac.stiki.doleno.absenin.view.history_detail.HistoryDetailActivity;

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
                Intent intent = new Intent(this, HistoryDetailActivity.class);
                intent.putExtra("absent_data", (Parcelable) absent);
                startActivity(intent);
            });
            binding.rvHistory.setAdapter(historyAdapter);
        });
    }
}
