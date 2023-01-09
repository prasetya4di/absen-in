package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.domain.GetEventParticipant
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import javax.inject.Inject

class GetEventParticipantImpl @Inject constructor(
    private val repository: EventParticipantRepository
) : GetEventParticipant {
    override fun execute(eventId: Long): List<EventParticipant> {
        return repository.read(eventId)
    }
}