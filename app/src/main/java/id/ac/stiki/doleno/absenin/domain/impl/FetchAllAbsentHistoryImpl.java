package id.ac.stiki.doleno.absenin.domain.impl;

import android.os.AsyncTask;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.domain.FetchAllAbsentHistory;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;

public class FetchAllAbsentHistoryImpl implements FetchAllAbsentHistory {
    private final AbsentRepository absentRepository;

    public FetchAllAbsentHistoryImpl(AbsentRepository absentRepository) {
        this.absentRepository = absentRepository;
    }

    @Override
    public Task<QuerySnapshot> execute() {
        return absentRepository.get()
                .addOnSuccessListener(result -> {
                    AsyncTask.execute(() -> {
                        List<Absent> absents = new ArrayList<>();
                        result.getDocuments().forEach(rawAbsent -> absents.add(new Absent(rawAbsent.getData())));
                        absentRepository.create(absents);
                    });
                });
    }
}
