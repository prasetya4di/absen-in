package id.ac.stiki.doleno.absenin.util.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedLocationModel(
    val locationName: String,
    val location: LatLng
) : Parcelable
