package id.ac.stiki.doleno.absenin.data.source.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface AbsentStore {
    Task<Void> createAbsent(Absent event, String email);

    Task<Void> updateAbsent(Absent event, String email);

    Task<QuerySnapshot> get(String email);
}
