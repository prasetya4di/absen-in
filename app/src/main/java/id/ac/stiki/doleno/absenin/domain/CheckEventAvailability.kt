package id.ac.stiki.doleno.absenin.domain

import kotlinx.coroutines.flow.Flow

interface CheckEventAvailability {
    fun execute(id: Long): Flow<Boolean>
}