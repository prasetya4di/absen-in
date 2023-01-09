package id.ac.stiki.doleno.absenin.repository;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface EventRepository {
    void create(Event Event);

    void create(List<Event> Event);

    LiveData<List<Event>> read();

    Event readById(int id);

    void delete();

    Task<Void> post(Event event);

    Task<QuerySnapshot> get();

    Task<QuerySnapshot> getActiveEvent();

    Task<QuerySnapshot> getAllEventByEmail(String email);
}
