package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.GetAllEvent
import id.ac.stiki.doleno.absenin.repository.EventRepository
import javax.inject.Inject

class GetAllEventImpl @Inject constructor(private val eventRepository: EventRepository) :
    GetAllEvent {
    override fun execute(): List<Event> {
        return eventRepository.read()
    }
}