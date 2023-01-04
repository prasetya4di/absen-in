package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.FetchAllEventByEmail
import id.ac.stiki.doleno.absenin.repository.EventRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class FetchAllEventByEmailImpl @Inject constructor(
    val eventRepository: EventRepository
) : FetchAllEventByEmail {
    override fun execute(email: String): Task<QuerySnapshot> {
        return eventRepository.getAllEventByEmail(email)
            .addOnSuccessListener {
                Executors.newSingleThreadExecutor().execute {
                    val listEvent = it.documents.map {
                        Event(it.data)
                    }
                    eventRepository.delete()
                    eventRepository.create(listEvent)
                }
            }
    }
}