package id.ac.stiki.doleno.absenin.view.participant.ui.event_join

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.R
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.databinding.ActivityEventJoinBinding
import id.ac.stiki.doleno.absenin.util.date.DateUtil
import id.ac.stiki.doleno.absenin.view.dialog.ConfirmDialog
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog
import id.ac.stiki.doleno.absenin.view.dialog.LoadingDialog
import id.ac.stiki.doleno.absenin.view.dialog.SuccessDialog
import id.ac.stiki.doleno.absenin.view.participant.ui.event_join.EventJoinState.*
import java.util.*

@AndroidEntryPoint
class EventJoinActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityEventJoinBinding
    private lateinit var viewModel: EventJoinViewModel
    private var event: Event? = null
    private lateinit var location: LatLng
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var googleMap: GoogleMap
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var errorDialog: ErrorDialog
    private lateinit var successDialog: SuccessDialog
    private lateinit var confirmDialog: ConfirmDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventJoinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        event = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("selected_event", Event::class.java)
        } else {
            intent.getParcelableExtra("selected_event")
        }

        viewModel = ViewModelProvider(this)[EventJoinViewModel::class.java]
        event?.let { viewModel.checkRegisterStatus(it) }

        loadingDialog = LoadingDialog(this)
        errorDialog = ErrorDialog(this, Dialog::dismiss) {
            it.dismiss()
            event?.let { event -> viewModel.joinEvent(event) }
        }
        successDialog = SuccessDialog(this)
        confirmDialog = ConfirmDialog(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment =
            supportFragmentManager.findFragmentById(binding.mapDetailEvent.id) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (event != null) {
            setData()
        }

        binding.btnOpenInMaps.setOnClickListener {
            val strUri =
                "http://maps.google.com/maps?q=loc:" + event?.location?.latitude + "," + event?.location?.longitude + " (" + event?.locationName + ")"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)
        }

        binding.btnRegisterEvent.setOnClickListener {
            confirmDialog.show(
                getString(
                    R.string.txt_register_event_confirmation,
                    event?.eventTitle
                )
            ) {
                it.dismiss()
                event?.let { event -> viewModel.joinEvent(event) }
            }
        }

        viewModel.eventJoinState.observe(this) {
            run {
                when (it) {
                    LOADING -> loadingDialog.show()
                    SUCCESS -> {
                        loadingDialog.dismiss()
                        successDialog.show(
                            getString(
                                R.string.txt_success_register_event,
                                event?.eventTitle,
                                DateUtil.dateToString(event?.eventDate ?: Date())
                            )
                        )
                    }
                    FAILED -> {
                        loadingDialog.dismiss()
                        errorDialog.show(getString(R.string.txt_failed_register_event))
                    }
                    REGISTERED -> {
                        loadingDialog.dismiss()
                        errorDialog.show(getString(R.string.txt_registered_event))
                    }
                }
            }
        }

        viewModel.isRegistered.observe(this) {
            run {
                binding.btnRegisterEvent.isEnabled = !it
            }
        }
    }

    private fun setData() {
        binding.txtTitle.text = event?.eventTitle
        binding.etTitle.setText(event?.eventTitle)
        binding.etDescription.setText(event?.eventDescription)
        binding.etDate.setText(DateUtil.dateToString(event?.eventDate ?: Date()))
        binding.etLocationName.setText(event?.locationName)
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

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.setAllGesturesEnabled(false)
        if (event == null) {
            moveToCurrentLocation()
        } else {
            changeLocation(event!!.location)
        }
    }
}