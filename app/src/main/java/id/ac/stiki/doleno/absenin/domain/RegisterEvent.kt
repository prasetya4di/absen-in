package id.ac.stiki.doleno.absenin.domain

import com.google.android.gms.tasks.Task
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.User

interface RegisterEvent {
    suspend fun execute(event: Event, user: User): Task<Void>
}