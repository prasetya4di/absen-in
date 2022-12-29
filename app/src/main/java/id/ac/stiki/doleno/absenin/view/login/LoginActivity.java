package id.ac.stiki.doleno.absenin.view.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.ActivityLoginBinding;
import id.ac.stiki.doleno.absenin.util.enums.Role;
import id.ac.stiki.doleno.absenin.view.admin.AdminActivity;
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog;
import id.ac.stiki.doleno.absenin.view.dialog.LoadingDialog;
import id.ac.stiki.doleno.absenin.view.main.MainActivity;
import id.ac.stiki.doleno.absenin.view.register.RegisterActivity;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    private LoadingDialog loadingDialog;
    private ErrorDialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(
                this,
                getString(R.string.txt_try_again),
                Dialog::dismiss,
                Dialog::dismiss);

        binding.btnLogin.setOnClickListener(v -> {
            if (isDataFullfilled()) {
                viewModel.login(
                        binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()
                );
            }
        });

        binding.btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        viewModel.loginState.observeForever(loginState -> {
            switch (loginState) {
                case LOADING:
                    loadingDialog.show();
                    break;
                case SUCCESS:
                    loadingDialog.dismiss();
                    Intent intent;
                    if (viewModel.getUserRole() == Role.EVENT_PLANNER) {
                        intent = new Intent(LoginActivity.this, AdminActivity.class);
                    } else {
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;
                case FAILED:
                    loadingDialog.dismiss();
                    errorDialog.show(getString(R.string.txt_general_error));
                    break;
                case INVALID_CREDENTIAL:
                    loadingDialog.dismiss();
                    errorDialog.show(getString(R.string.txt_invalid_credential_error));
                    break;
            }
        });
    }

    private boolean isDataFullfilled() {
        return !binding.etEmail.getText().toString().isEmpty()
                && !binding.etPassword.getText().toString().isEmpty();
    }
}