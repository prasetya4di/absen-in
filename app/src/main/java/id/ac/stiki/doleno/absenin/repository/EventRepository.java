package id.ac.stiki.doleno.absenin.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface EventRepository {
    void create(Event Event);

    List<Event> read();

    Event readById(int id);

    Task<Void> post(Event event);

    Task<QuerySnapshot> get();

    Task<QuerySnapshot> getActiveEvent();
}
