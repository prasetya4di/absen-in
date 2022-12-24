package id.ac.stiki.doleno.absenin.view.admin.ui.my_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.FragmentMyProfileBinding;
import id.ac.stiki.doleno.absenin.view.dialog.ConfirmDialog;
import id.ac.stiki.doleno.absenin.view.login.LoginActivity;

@AndroidEntryPoint
public class MyProfileFragment extends Fragment {
    private FragmentMyProfileBinding binding;
    private MyProfileViewModel viewModel;
    private ConfirmDialog confirmDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        confirmDialog = new ConfirmDialog(this.getActivity());

        viewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        viewModel.getUser();
        viewModel.myProfileState.observeForever(state -> {
            switch (state) {
                case LOADING:
                    break;
                case SUCCESS:
                case FAILED:
                    binding.etEmail.setText(viewModel.user.email);
                    binding.etName.setText(viewModel.user.name);
                    binding.etRole.setText(viewModel.user.role.getText());
                    break;
            }
        });

        binding.btnLogout.setOnClickListener(v -> {
                    confirmDialog.show(this.getActivity().getString(R.string.logout_message), click -> {
                        Intent intent = new Intent(this.getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        viewModel.logout();
                    });
                }
        );

        return binding.getRoot();
    }
}