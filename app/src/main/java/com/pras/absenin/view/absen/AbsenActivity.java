package com.pras.absenin.view.absen;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pras.absenin.databinding.ActivityAbsenBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AbsenActivity extends AppCompatActivity {
    private ActivityAbsenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
