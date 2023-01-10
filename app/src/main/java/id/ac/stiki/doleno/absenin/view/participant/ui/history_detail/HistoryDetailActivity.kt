package id.ac.stiki.doleno.absenin.view.participant.ui.history_detail

import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.databinding.ActivityAbsentHistoryDetailBinding
import id.ac.stiki.doleno.absenin.util.date.DateUtil
import java.util.*

class HistoryDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityAbsentHistoryDetailBinding
    private lateinit var location: LatLng
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var googleMap: GoogleMap
    private var absent: Absent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsentHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        absent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("absent_data", Absent::class.java)
        } else {
            intent.getParcelableExtra("absent_data")
        }

        if (absent != null) {
            setData()
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment =
            supportFragmentManager.findFragmentById(binding.mapDetailEvent.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.setAllGesturesEnabled(false)
        if (absent == null) {
            moveToCurrentLocation()
        } else {
            changeLocation(absent!!.location)
        }
    }

    private fun setData() {
        binding.txtTitle.text = absent?.absentTitle
        binding.etTitle.setText(absent?.absentTitle)
        binding.etDescription.setText(absent?.absentDescription)
        binding.etDate.setText(DateUtil.dateToString(absent?.absentDate ?: Date()))
        binding.etLocationName.setText(absent?.locationName)
    }

    private fun changeLocation(loc: LatLng) {
        googleMap.clear()
        location = loc
        addMarker(loc)
        moveCamera(loc)
    }

    private fun addMarker(location: LatLng) {
        googleMap.addMarker(MarkerOptions().position(location))
    }

    private fun moveCamera(loc: LatLng) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 15f))
    }

    @SuppressLint("MissingPermission")
    private fun moveToCurrentLocation() {
        fusedLocationProviderClient
            .lastLocation
            .addOnSuccessListener(this) { location: Location? ->
                if (location != null) {
                    changeLocation(
                        LatLng(
                            location.latitude,
                            location.longitude
                        )
                    )
                }
            }
    }
}