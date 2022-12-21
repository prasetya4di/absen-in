package id.ac.stiki.doleno.absenin.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface AbsentRepository {
    void create(Absent absent);

    List<Absent> read();

    Absent read(int id);

    Task<Void> post(Absent absent);

    Task<QuerySnapshot> get();
}
