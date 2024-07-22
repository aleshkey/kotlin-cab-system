package org.modsen.passengerservice.payload.response

data class PassengerResponse(
        var id: Long,

        var firstName: String,

        var lastName: String,

        var email: String
)
