package id.ac.stiki.doleno.absenin.domain

interface CheckEventAvailability {
    fun execute(id: Long): Boolean
}