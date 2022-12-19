package id.ac.stiki.doleno.absenin.util.converters;

import androidx.room.TypeConverter;

import com.google.android.gms.maps.model.LatLng;

public class LatLngConverter {
    @TypeConverter
    public LatLng fromString(String value) {
        String[] listLatLng = value.split(",");
        double lat = Double.parseDouble(listLatLng[0]);
        double lng = Double.parseDouble(listLatLng[1]);
        return new LatLng(lat, lng);
    }

    @TypeConverter
    public String latLngToString(LatLng latLng) {
        return latLng.latitude + "," + latLng.longitude;
    }
}
