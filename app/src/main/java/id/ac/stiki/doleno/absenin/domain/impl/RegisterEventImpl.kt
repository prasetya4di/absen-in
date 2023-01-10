package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.tasks.Task
import id.ac.stiki.doleno.absenin.data.database.table.Table
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.data.entity.User
import id.ac.stiki.doleno.absenin.data.source.network.GlobalStore
import id.ac.stiki.doleno.absenin.domain.RegisterEvent
import id.ac.stiki.doleno.absenin.repository.AbsentRepository
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class RegisterEventImpl @Inject constructor(
    private val absentRepository: AbsentRepository,
    private val eventParticipantRepository: EventParticipantRepository,
    private val globalStore: GlobalStore
) : RegisterEvent {
    override suspend fun execute(event: Event, user: User): Task<Void> {
        val idParticipant = globalStore.getLatestId(Table.EVENT_PARTICIPANT.text).plus(1)
        val idAbsent = event.uid
        val eventParticipant = EventParticipant(event, user).apply { uid = idParticipant }
        val absent = Absent(event).apply { uid = idAbsent }
        return eventParticipantRepository
            .post(eventParticipant)
            .addOnSuccessListener {
                Executors.newSingleThreadExecutor().execute {
                    eventParticipantRepository.create(eventParticipant)
                }
            }
            .continueWithTask {
                absentRepository.post(absent, user.email)
            }
            .addOnSuccessListener {
                Executors.newSingleThreadExecutor().execute {
                    absentRepository.create(absent)
                }
            }
    }
}