package id.ac.stiki.doleno.absenin.repository;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface AbsentRepository {
    void create(Absent absent);

    void create(List<Absent> absent);

    LiveData<List<Absent>> read();

    LiveData<List<Absent>> readByStatus(List<String> status);

    Absent read(int id);

    void delete();

    Task<Void> post(Absent absent, String email);

    Task<QuerySnapshot> get();
}
