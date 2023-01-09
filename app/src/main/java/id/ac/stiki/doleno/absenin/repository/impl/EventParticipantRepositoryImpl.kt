package id.ac.stiki.doleno.absenin.repository.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.AggregateQuerySnapshot
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao
import id.ac.stiki.doleno.absenin.data.source.network.EventParticipantStore
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import javax.inject.Inject

class EventParticipantRepositoryImpl @Inject constructor(
    private val eventParticipantDao: EventParticipantDao,
    private val eventParticipantStore: EventParticipantStore
) : EventParticipantRepository {
    override fun read(eventId: Long): List<EventParticipant> {
        return eventParticipantDao.getAll(eventId)
    }

    override fun create(eventParticipant: EventParticipant) {
        eventParticipantDao.insert(eventParticipant)
    }

    override fun create(eventParticipant: List<EventParticipant>) {
        eventParticipantDao.insert(eventParticipant)
    }

    override fun post(eventParticipant: EventParticipant): Task<DocumentReference> {
        return eventParticipantStore.create(eventParticipant)
    }

    override fun get(eventId: Int): Task<QuerySnapshot> {
        return eventParticipantStore[eventId]
    }

    override fun count(event: Event, email: String): Task<AggregateQuerySnapshot> {
        return eventParticipantStore.count(event, email)
    }
}