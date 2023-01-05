package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.CheckRegisteredStatus
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CheckRegisteredStatusImpl @Inject constructor(
    val eventParticipantRepository: EventParticipantRepository
) : CheckRegisteredStatus {
    override fun execute(event: Event, email: String): Flow<Boolean> {
        return flow {
            val participateCount = eventParticipantRepository.count(event, email)
                .await()
                .count
            emit(participateCount > 0)
        }
    }
}