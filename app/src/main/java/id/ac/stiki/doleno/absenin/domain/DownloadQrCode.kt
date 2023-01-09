package id.ac.stiki.doleno.absenin.domain

import id.ac.stiki.doleno.absenin.data.entity.Event

interface DownloadQrCode {
    fun execute(event: Event)
}