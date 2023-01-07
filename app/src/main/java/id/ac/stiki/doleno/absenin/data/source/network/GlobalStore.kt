package id.ac.stiki.doleno.absenin.data.source.network

interface GlobalStore {
    suspend fun getLatestId(table: String): Long
}