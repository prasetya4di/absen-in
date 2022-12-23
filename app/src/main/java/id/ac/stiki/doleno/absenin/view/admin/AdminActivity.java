package id.ac.stiki.doleno.absenin.view.admin;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.ActivityAdminBinding;
import id.ac.stiki.doleno.absenin.view.admin.ui.MyEventFragment;
import id.ac.stiki.doleno.absenin.view.admin.ui.MyProfileFragment;

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
            switch (item.getItemId()) {
                case R.id.my_event_page:
                    setCurrentPage(myEventFragment);
                    return true;
                case R.id.profile_page:
                    setCurrentPage(myProfileFragment);
                    return true;
                default:
                    setCurrentPage(myEventFragment);
            }
            return false;
        });
    }

    private void setCurrentPage(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flAdmin, fragment).commit();
    }
}