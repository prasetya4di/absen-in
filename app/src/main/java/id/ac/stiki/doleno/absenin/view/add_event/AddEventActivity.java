package id.ac.stiki.doleno.absenin.view.add_event;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.ActivityAddEventBinding;
import id.ac.stiki.doleno.absenin.util.date.DateUtil;
import id.ac.stiki.doleno.absenin.util.model.SelectedLocationModel;
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog;
import id.ac.stiki.doleno.absenin.view.dialog.LoadingDialog;
import id.ac.stiki.doleno.absenin.view.map_picker.MapPickerActivity;

@SuppressLint("MissingPermission")
@AndroidEntryPoint
public class AddEventActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityAddEventBinding binding;
    private LoadingDialog loadingDialog;
    private ErrorDialog errorDialog;
    private LatLng selectedLocation;
    private SelectedLocationModel selectedLocationModel;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap googleMap;
    private Date selectedDate;
    private final ActivityResultLauncher<Intent> selectLocation = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        selectedLocationModel = result.getData().getParcelableExtra("selected_location_model", SelectedLocationModel.class);
                    } else {
                        selectedLocationModel = result.getData().getParcelableExtra("selected_location_model");
                    }
                    selectedLocation = selectedLocationModel.getLocation();
                    changeLocation(selectedLocationModel.getLocation());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AddEventViewModel viewModel = new ViewModelProvider(this).get(AddEventViewModel.class);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAddEvent);
        mapFragment.getMapAsync(this);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(this, Dialog::dismiss, Dialog::dismiss);

        viewModel.myEventState.observeForever(state -> {
            switch (state) {
                case LOADING:
                    loadingDialog.show();
                    break;
                case FAILED:
                    loadingDialog.dismiss();
                    errorDialog.show(getString(R.string.txt_general_error));
                    break;
                case SUCCESS:
                    loadingDialog.dismiss();
                    onBackPressed();
                    break;
            }
        });

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            getUserCurrentLocation();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

        binding.etDate.setOnClickListener(v -> {
            pickDate();
        });

        binding.layoutSelectLocation.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapPickerActivity.class);
            intent.putExtra("selected_location", this.selectedLocation);
            selectLocation.launch(intent);
        });

        binding.btnAddEvent.setOnClickListener(v -> {
            if (isDataComplete()) {
                viewModel.addEvent(
                        binding.etTitle.getText().toString(),
                        binding.etDescription.getText().toString(),
                        selectedDate,
                        selectedLocationModel
                );
            } else {
                errorDialog.show(getString(R.string.txt_data_incomplete_error));
            }
        });

        binding.imgBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setAllGesturesEnabled(false);
    }

    private void getUserCurrentLocation() {
        fusedLocationProviderClient
                .getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        selectedLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        changeLocation(selectedLocation);
                    }
                });
    }

    private void changeLocation(LatLng loc) {
        googleMap.clear();
        selectedLocation = loc;
        addMarker(loc);
        moveCamera(loc);
    }

    private void addMarker(LatLng location) {
        googleMap.addMarker(new MarkerOptions().position(location));
    }

    private void moveCamera(LatLng loc) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
    }

    private void pickDate() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    selectedDate = cal.getTime();
                    binding.etDate.setText(getString(R.string.txt_date, dayOfMonth, DateUtil.Companion.getMonthName(month), year));
                }, currentYear, currentMonth, currentDay);

        datePickerDialog.show();
    }

    private Boolean isDataComplete() {
        return !binding.etTitle.getText().toString().isEmpty()
                && !binding.etDescription.getText().toString().isEmpty()
                && selectedDate != null
                && selectedLocationModel != null;
    }
}