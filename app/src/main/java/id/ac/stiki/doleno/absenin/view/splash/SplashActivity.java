package id.ac.stiki.doleno.absenin.view.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.ActivitySplashBinding;
import id.ac.stiki.doleno.absenin.util.enums.Role;
import id.ac.stiki.doleno.absenin.view.admin.AdminActivity;
import id.ac.stiki.doleno.absenin.view.login.LoginActivity;
import id.ac.stiki.doleno.absenin.view.participant.ParticipantActivity;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity implements MultiplePermissionsListener {
    private ActivitySplashBinding binding;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .withListener(this)
                .check();

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

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
        if (!multiplePermissionsReport.areAllPermissionsGranted()) {
            Toast.makeText(this, R.string.permission_denied_text, Toast.LENGTH_SHORT).show();
        }
        viewModel.checkLoggedInStatus();
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
        permissionToken.continuePermissionRequest();
    }
}