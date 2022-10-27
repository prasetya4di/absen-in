package com.pras.absenin.view.absen;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pras.absenin.databinding.ActivityAbsenBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AbsenActivity extends AppCompatActivity {
    private ActivityAbsenBinding binding;
    private AbsenViewModel absenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        absenViewModel = new ViewModelProvider(this).get(AbsenViewModel.class);
    }
}
