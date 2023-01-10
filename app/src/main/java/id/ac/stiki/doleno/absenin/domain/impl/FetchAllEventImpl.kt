package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.FetchAllEvent
import id.ac.stiki.doleno.absenin.repository.EventRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class FetchAllEventImpl @Inject constructor(private val eventRepository: EventRepository) :
    FetchAllEvent {
    override fun execute(): Task<QuerySnapshot> {
        return eventRepository
            .activeEvent
            .addOnSuccessListener { querySnapshots: QuerySnapshot ->
                Executors.newSingleThreadExecutor().execute {
                    val listEvent: List<Event> = querySnapshots.documents.map { document ->
                        Event(document.data)
                    }
                    eventRepository.delete()
                    eventRepository.create(listEvent)
                }
            }
    }
}