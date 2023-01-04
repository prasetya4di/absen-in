package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.CheckRegisteredStatus
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import javax.inject.Inject

class CheckRegisteredStatusImpl @Inject constructor(
    val eventParticipantRepository: EventParticipantRepository
) : CheckRegisteredStatus {
    override fun execute(event: Event, email: String): Boolean {
        return eventParticipantRepository.count(event, email) > 0
    }
}