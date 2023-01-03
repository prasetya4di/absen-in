package id.ac.stiki.doleno.absenin.view.history_detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.databinding.ActivityHistoryDetailBinding;

@AndroidEntryPoint
public class HistoryDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityHistoryDetailBinding binding;
    private Absent absent;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.detailMap);
        mapFragment.getMapAsync(this);
        absent = getIntent().getParcelableExtra("absent_data");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        if (absent != null) {
            Objects.requireNonNull(gMap.addMarker(new MarkerOptions()
                            .position(absent.location)
                            .title(absent.absentTitle)))
                    .showInfoWindow();
            moveCamera(absent.location);
        }
    }

    private void moveCamera(LatLng loc) {
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
    }
}
