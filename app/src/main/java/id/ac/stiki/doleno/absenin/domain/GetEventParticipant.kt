package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant

interface GetEventParticipant {
    fun execute(eventId: Long): List<EventParticipant>
}