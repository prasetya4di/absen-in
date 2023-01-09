package id.ac.stiki.doleno.absenin.repository;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;

public interface EventParticipantRepository {
    LiveData<List<EventParticipant>> read(int eventId);

    void create(EventParticipant eventParticipant);

    void create(List<EventParticipant> eventParticipant);

    Task<DocumentReference> post(EventParticipant eventParticipant);

    Task<QuerySnapshot> get(int eventId);

    Task<AggregateQuerySnapshot> count(Event event, String email);
}