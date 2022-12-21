package id.ac.stiki.doleno.absenin.data.source.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface EventStore {
    Task<Void> createEvent(Event event);

    Task<Void> updateEvent(Event event);

    Task<QuerySnapshot> getAllEvent();

    Task<QuerySnapshot> getAllActiveEvent();
}
