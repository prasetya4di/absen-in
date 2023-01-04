package id.ac.stiki.doleno.absenin.view.participant.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.domain.GetAllAbsentHistory;

@HiltViewModel
public class HistoryViewModel extends ViewModel {

    LiveData<List<Absent>> listAbsent;

    @Inject
    public HistoryViewModel(GetAllAbsentHistory getAllAbsentHistory) {
        listAbsent = getAllAbsentHistory.execute();
    }
}
