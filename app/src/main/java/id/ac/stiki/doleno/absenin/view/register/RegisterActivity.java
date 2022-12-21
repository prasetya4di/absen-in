package id.ac.stiki.doleno.absenin.view.register;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.databinding.ActivityRegisterBinding;
import id.ac.stiki.doleno.absenin.util.enums.Role;
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog;
import id.ac.stiki.doleno.absenin.view.dialog.LoadingDialog;
import id.ac.stiki.doleno.absenin.view.main.MainActivity;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    private LoadingDialog loadingDialog;
    private ErrorDialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(
                this,
                getString(R.string.txt_try_again),
                Dialog::dismiss,
                Dialog::dismiss);

        binding.btnRegister.setOnClickListener(v -> {
            if (isDataFullfilled()) {
                viewModel.register(
                        binding.etPassword.getText().toString(),
                        new User(
                                binding.etEmail.getText().toString(),
                                binding.etName.getText().toString(),
                                Role.fromString(binding.spinnerRole.getSelectedItem().toString())
                        )
                );
            } else {
                errorDialog.show(getString(R.string.txt_data_incomplete_error));
            }
        });

        viewModel.registerState.observeForever(registerState -> {
            loadingDialog.dismiss();
            switch (registerState) {
                case LOADING:
                    loadingDialog.show();
                    break;
                case SUCCESS:
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case FAILED:
                    errorDialog.show(getString(R.string.txt_general_error));
                    break;
                case PASSWORD_WEAK:
                    errorDialog.show(getString(R.string.txt_password_error));
                    break;
                case EMAIL_TAKEN:
                    errorDialog.show(getString(R.string.txt_email_taken_error));
                    break;
            }
        });

        binding.imgBack.setOnClickListener(this::back);
    }

    private boolean isDataFullfilled() {
        return !binding.etEmail.getText().toString().isEmpty()
                && !binding.etPassword.getText().toString().isEmpty()
                && !binding.etName.getText().toString().isEmpty();
    }

    private void back(View v) {
        onBackPressed();
    }
}