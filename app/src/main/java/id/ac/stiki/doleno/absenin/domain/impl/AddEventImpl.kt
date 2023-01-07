package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.tasks.Task
import id.ac.stiki.doleno.absenin.data.database.table.Table
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.source.network.GlobalStore
import id.ac.stiki.doleno.absenin.domain.AddEvent
import id.ac.stiki.doleno.absenin.repository.EventRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class AddEventImpl @Inject constructor(
    private val repository: EventRepository,
    private val globalStore: GlobalStore
) : AddEvent {
    override suspend fun execute(event: Event): Task<Void> {
        val id = globalStore.getLatestId(Table.ABSENT.text)
        event.uid = id
        return repository
            .post(event)
            .addOnSuccessListener {
                Executors.newSingleThreadExecutor().execute {
                    repository.create(event)
                }
            }
    }
}