package id.ac.stiki.doleno.absenin.view.absen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.domain.DoAbsent;
import id.ac.stiki.doleno.absenin.domain.GetCurrentLocation;
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus;

@HiltViewModel
public class AbsenViewModel extends ViewModel {
    private final DoAbsent doAbsent;
    private final GetCurrentLocation getCurrentLocation;
    private final MutableLiveData<QRCodeResultStatus> _resultStatus = new MutableLiveData<>();
    LiveData<QRCodeResultStatus> resultStatus = _resultStatus;

    @Inject
    public AbsenViewModel(DoAbsent doAbsent, GetCurrentLocation getCurrentLocation) {
        this.doAbsent = doAbsent;
        this.getCurrentLocation = getCurrentLocation;
    }

    public void doAbsent(String result) {
        getCurrentLocation.execute()
                .addOnSuccessListener(location -> {
                    LatLng currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    doAbsent(result, currentLoc);
                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                    _resultStatus.postValue(QRCodeResultStatus.INVALID_LOCATION);
                });
    }

    private void doAbsent(String result, LatLng currentLoc) {
        Executors.newSingleThreadExecutor().execute(() -> {
            QRCodeResultStatus resultStatus = doAbsent.execute(result, currentLoc);
            _resultStatus.postValue(resultStatus);
        });
    }
}
