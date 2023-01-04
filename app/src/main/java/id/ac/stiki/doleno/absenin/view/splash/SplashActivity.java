package id.ac.stiki.doleno.absenin.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.databinding.ActivitySplashBinding;
import id.ac.stiki.doleno.absenin.util.enums.Role;
import id.ac.stiki.doleno.absenin.view.admin.AdminActivity;
import id.ac.stiki.doleno.absenin.view.login.LoginActivity;
import id.ac.stiki.doleno.absenin.view.participant.ParticipantActivity;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        viewModel.checkLoggedInStatus();

        viewModel.splashState.observeForever(splashState -> {
            Intent intent;
            switch (splashState) {
                case LOADING:
                    break;
                case NOT_LOGGED_IN:
                    intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;
                case LOGGED_IN:
                    if (viewModel.getUserRole() == Role.EVENT_PLANNER) {
                        intent = new Intent(this, AdminActivity.class);
                    } else {
                        intent = new Intent(this, ParticipantActivity.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;
            }
        });
    }
}