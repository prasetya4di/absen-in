package id.ac.stiki.doleno.absenin.view.participant.ui.history;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.domain.GetAbsentByStatus;
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus;

@HiltViewModel
public class HistoryViewModel extends ViewModel {

    List<Absent> listAbsent;

    @Inject
    public HistoryViewModel(GetAbsentByStatus getAbsentByStatus) {
        listAbsent = getAbsentByStatus.execute(Arrays.asList(
                AbsentStatus.ATTENDED.getText(),
                AbsentStatus.DID_NOT_ATTEND.getText()
        ));
    }
}
