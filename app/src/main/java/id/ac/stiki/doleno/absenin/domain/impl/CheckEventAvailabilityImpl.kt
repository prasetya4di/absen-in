package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.domain.CheckEventAvailability
import id.ac.stiki.doleno.absenin.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckEventAvailabilityImpl @Inject constructor(
    private val eventRepository: EventRepository
) : CheckEventAvailability {
    override fun execute(id: Long): Flow<Boolean> {
        return flow {
            emit(eventRepository.checkEventAvailability(id))
        }
    }
}