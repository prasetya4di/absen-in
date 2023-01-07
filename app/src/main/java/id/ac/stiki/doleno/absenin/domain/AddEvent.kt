package id.ac.stiki.doleno.absenin.domain

import com.google.android.gms.tasks.Task
import id.ac.stiki.doleno.absenin.data.entity.Event

interface AddEvent {
    suspend fun execute(event: Event): Task<Void>
}