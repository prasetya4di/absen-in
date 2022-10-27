package com.pras.absenin.view.absen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.util.qrcode.QRCodeResultStatus;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AbsenViewModel extends ViewModel {
    private final DoAbsent doAbsent;
    private final MutableLiveData<QRCodeResultStatus> _resultStatus = new MutableLiveData<>();
    LiveData<QRCodeResultStatus> resultStatus = _resultStatus;

    @Inject
    public AbsenViewModel(DoAbsent doAbsent) {
        this.doAbsent = doAbsent;
    }

    public void doAbsent(String result) {
        Executors.newSingleThreadExecutor().execute(() -> {
            QRCodeResultStatus resultStatus = doAbsent.execute(result);
            _resultStatus.postValue(resultStatus);
        });
    }
}
