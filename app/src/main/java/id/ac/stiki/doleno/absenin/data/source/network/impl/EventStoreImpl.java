package id.ac.stiki.doleno.absenin.data.source.network.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

import id.ac.stiki.doleno.absenin.data.database.table.Column;
import id.ac.stiki.doleno.absenin.data.database.table.Table;
import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.source.network.EventStore;

public class EventStoreImpl implements EventStore {
    private final FirebaseFirestore firestore;

    public EventStoreImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Task<Void> createEvent(Event event) {
        return getCollection().document(event.getDocumentPath()).set(event.toMap());
    }

    @Override
    public Task<Void> updateEvent(Event event) {
        return getCollection().document(event.getDocumentPath()).set(event.toMap());
    }

    @Override
    public Task<QuerySnapshot> getAllEvent() {
        return getCollection().get();
    }

    @Override
    public Task<QuerySnapshot> getAllActiveEvent() {
        return getCollection().whereGreaterThan("event_date", new Date()).get();
    }

    @Override
    public Task<QuerySnapshot> getEventByEmail(String email) {
        return getCollection().whereEqualTo(Column.Event.ORGANIZER_MAIL.getColumnName(), email).get();
    }

    private CollectionReference getCollection() {
        return firestore.collection(Table.EVENT.getText());
    }
}
