package com.pras.absenin.view.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.domain.GetAllAbsentHistory;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HistoryViewModel extends ViewModel {
    private final GetAllAbsentHistory getAllAbsentHistory;

    private final MutableLiveData<List<Absent>> _listAbsent = new MutableLiveData<>();
    LiveData<List<Absent>> listAbsent = _listAbsent;

    @Inject
    public HistoryViewModel(GetAllAbsentHistory getAllAbsentHistory) {
        this.getAllAbsentHistory = getAllAbsentHistory;
    }

    public void getAllAbsentHistory() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Absent> absents = getAllAbsentHistory.execute();
            _listAbsent.postValue(absents);
        });
    }
}
