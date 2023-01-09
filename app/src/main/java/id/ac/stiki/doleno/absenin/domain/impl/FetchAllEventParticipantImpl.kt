package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.domain.FetchAllEventParticipant
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import javax.inject.Inject

class FetchAllEventParticipantImpl @Inject constructor(private val eventParticipantRepository: EventParticipantRepository) :
    FetchAllEventParticipant {
    override fun execute(eventId: Long): Task<QuerySnapshot> {
        return eventParticipantRepository.get(eventId)
            .addOnSuccessListener { result: QuerySnapshot ->
                val eventParticipants: List<EventParticipant> = result.documents.map {
                    EventParticipant(it.data)
                }
                eventParticipantRepository.create(eventParticipants)
            }
    }
}