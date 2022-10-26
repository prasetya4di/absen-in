package com.pras.absenin.view.absent_result;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pras.absenin.databinding.ActivityAbsentFailedBinding;

public class AbsentFailedActivity extends AppCompatActivity {
    private ActivityAbsentFailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsentFailedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
