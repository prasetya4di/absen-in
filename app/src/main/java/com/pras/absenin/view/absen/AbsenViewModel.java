package com.pras.absenin.view.absen;

import androidx.lifecycle.ViewModel;

import com.pras.absenin.domain.DoAbsent;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AbsenViewModel extends ViewModel {
    private final DoAbsent doAbsent;

    @Inject
    public AbsenViewModel(DoAbsent doAbsent) {
        this.doAbsent = doAbsent;
    }
}
