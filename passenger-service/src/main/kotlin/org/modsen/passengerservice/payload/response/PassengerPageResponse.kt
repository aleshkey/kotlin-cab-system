package org.modsen.passengerservice.payload.response

import org.modsen.passengerservice.payload.general.PageParams

data class PassengerPageResponse(

    val pageParams: PageParams,

    val content: List<PassengerResponse>

)
