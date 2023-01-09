package id.ac.stiki.doleno.absenin.repository.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.source.local.EventDao
import id.ac.stiki.doleno.absenin.data.source.network.EventStore
import id.ac.stiki.doleno.absenin.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val eventStore: EventStore
) :
    EventRepository {
    override fun create(event: Event) {
        eventDao.insert(event)
    }

    override fun create(event: List<Event>) {
        eventDao.insert(event)
    }

    override fun read(): List<Event> {
        return eventDao.all
    }

    override fun readById(id: Int): Event {
        return eventDao.getById(id)
    }

    override fun delete() {
        eventDao.delete()
    }

    override fun post(event: Event?): Task<Void> {
        return eventStore.createEvent(event)
    }

    override fun get(): Task<QuerySnapshot> {
        return eventStore.allEvent
    }

    override val activeEvent: Task<QuerySnapshot>
        get() = eventStore.allActiveEvent

    override fun getAllEventByEmail(email: String): Task<QuerySnapshot> {
        return eventStore.getEventByEmail(email)
    }
}