package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.Absent

interface GetAbsentByStatus {
    fun execute(status: List<String>): List<Absent>
}