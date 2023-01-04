package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.Event

interface CheckRegisteredStatus {
    fun execute(event: Event, email: String): Boolean
}