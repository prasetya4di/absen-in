package id.ac.stiki.doleno.absenin.data.source.network.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import id.ac.stiki.doleno.absenin.data.database.table.Column
import id.ac.stiki.doleno.absenin.data.database.table.Table
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.data.source.network.EventParticipantStore
import javax.inject.Inject

class EventParticipantStoreImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    EventParticipantStore {
    override fun create(eventParticipant: EventParticipant): Task<DocumentReference> {
        return getCollection(eventParticipant.eventId)
            .add(eventParticipant.toMap())
    }

    override fun get(eventId: Long): Task<QuerySnapshot> {
        return getCollection(eventId).get()
    }

    override fun get(eventId: Long, email: String): Task<QuerySnapshot> {
        return getCollection(eventId)
            .whereEqualTo("email", email)
            .get()
    }

    override fun update(eventParticipant: EventParticipant): Task<QuerySnapshot> {
        return getCollection(eventParticipant.eventId)
            .whereEqualTo("uid", eventParticipant.uid)
            .get()
            .addOnSuccessListener {
                for (document in it.documents) {
                    getCollection(eventParticipant.eventId).document(document.id)
                        .set(eventParticipant.toMap())
                }
            }
    }

    override fun count(event: Event, email: String): Task<AggregateQuerySnapshot> {
        return getCollection(event.uid)
            .whereEqualTo(Column.EventParticipant.EMAIL.columnName, email)
            .count()[AggregateSource.SERVER]
    }

    private fun getCollection(eventId: Long): CollectionReference {
        return firestore.collection(Table.EVENT_PARTICIPANT.text + "_" + eventId)
    }
}