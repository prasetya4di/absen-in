package id.ac.stiki.doleno.absenin.data.source.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface AbsentStore {
    Task<Void> createAbsent(Absent absent, String email);

    Task<Void> updateAbsent(Absent absent, String email);

    Task<QuerySnapshot> get(String email);
}
