package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.Event
import kotlinx.coroutines.flow.Flow

interface CheckRegisteredStatus {
    fun execute(event: Event, email: String): Flow<Boolean>
}