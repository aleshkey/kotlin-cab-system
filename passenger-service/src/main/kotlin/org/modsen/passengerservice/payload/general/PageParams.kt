package org.modsen.passengerservice.payload.general

data class PageParams(
        val size: Int = 20,

        val page: Int = 1,

        val sort: String = "id",

        val direction: String = "asc"
)
