package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.Absent

interface GetAllAbsentHistory {
    fun execute(): List<Absent>
}