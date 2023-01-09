package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.Event

interface GetAllEvent {
    fun execute(): List<Event>
}