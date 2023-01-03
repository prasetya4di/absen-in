package id.ac.stiki.doleno.absenin.view.map_picker

import android.annotation.SuppressLint
import android.app.Dialog
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.ac.stiki.doleno.absenin.R
import id.ac.stiki.doleno.absenin.databinding.ActivityMapPickerBinding
import id.ac.stiki.doleno.absenin.util.model.Item
import id.ac.stiki.doleno.absenin.util.retrofit.RetrofitInstance
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MapPickerActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapPickerBinding
    private val retrofitInstance = RetrofitInstance.create()

    private var hasFetch = false
    private var animateMarker = true

    private lateinit var errorDialog: ErrorDialog
    private var selectedLocation: LatLng? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var bottomSheet: BottomSheetBehavior<View>
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMapPickerBinding = ActivityMapPickerBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        selectedLocation = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("selected_location", LatLng::class.java)
        } else {
            intent.getParcelableExtra("selected_location")
        }

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet.bottomSheet)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        binding.progressCircular.visibility = View.GONE

        errorDialog = ErrorDialog(
            this,
            Dialog::dismiss,
            Dialog::dismiss
        )

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun getLocation(latLng: LatLng, done: (Item) -> Unit) {
        val at = "${latLng.latitude},${latLng.longitude}"
        if (!hasFetch) {
            animateMarker = false
            binding.progressCircular.visibility = View.VISIBLE
            GlobalScope.launch {
                try {
                    val places = retrofitInstance.getLocation(at).items
                    runOnUiThread {
                        if (places.isNotEmpty()) {
                            binding.progressCircular.visibility = View.GONE
                            done.invoke(places.first())
                        }
                    }
                } catch (e: Throwable) {
                    errorDialog.show(getString(R.string.txt_general_error))
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map

        if (selectedLocation == null) {
            getUserCurrentLocation()
                .addOnSuccessListener {
                    map.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                it.latitude,
                                it.longitude
                            ), 18f
                        )
                    )
                }
        } else {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation!!, 18f))
        }

        val oldPosition = map.cameraPosition.target

        map.setOnCameraMoveStartedListener {
            // drag started
            if (animateMarker) {
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

                binding.iconMarker.animate().translationY(-50f).start()
                binding.iconMarkerShadow.animate().withStartAction {
                    binding.iconMarkerShadow.setPadding(10)
                }.start()
            }

            hasFetch = false
        }

        map.setOnCameraIdleListener {
            val newPosition = map.cameraPosition.target
            if (newPosition != oldPosition) {
                // drag ended
                binding.iconMarker.animate().translationY(0f).start()
                binding.iconMarkerShadow.animate().withStartAction {
                    binding.iconMarkerShadow.setPadding(0)
                }.start()

                getLocation(newPosition) { item ->
                    bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    val position = item.position
                    val findLocation = LatLng(position.lat, position.lng)

                    map.animateCamera(CameraUpdateFactory.newLatLng(findLocation), 200,
                        object : GoogleMap.CancelableCallback {
                            override fun onFinish() {
                                hasFetch = true
                                animateMarker = true
                            }

                            override fun onCancel() {
                                animateMarker = true
                            }

                        })

                    val titlePlace = item.title
                    val address = item.address.label

                    binding.bottomSheet.textTitle.text = titlePlace
                    binding.bottomSheet.textAddress.text = address
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserCurrentLocation(): Task<Location> {
        return fusedLocationProviderClient.lastLocation
    }
}