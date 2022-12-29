package id.ac.stiki.doleno.absenin.view.admin;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.ActivityAdminBinding;
import id.ac.stiki.doleno.absenin.view.admin.ui.my_event.MyEventFragment;
import id.ac.stiki.doleno.absenin.view.admin.ui.my_profile.MyProfileFragment;

@AndroidEntryPoint
public class AdminActivity extends AppCompatActivity {
    private ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        MyEventFragment myEventFragment = new MyEventFragment();
        MyProfileFragment myProfileFragment = new MyProfileFragment();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile_page) {
                setCurrentPage(myProfileFragment);
            } else {
                setCurrentPage(myEventFragment);
            }
            return true;
        });

        setCurrentPage(myEventFragment);
    }

    private void setCurrentPage(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flAdmin, fragment).commit();
    }
}