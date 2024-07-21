package org.modsen.passengerservice.payload.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PassengerRequest(
    val id: Long?,

    @field:NotNull(message = "First name can't be empty")
    @field:Size(min = 1, message = "First name can't be empty")
    val firstName: String,

    @field:NotNull(message = "Last name can't be empty")
    @field:Size(min = 1, message = "Last name can't be empty")
    val lastName: String,

    @field:Email(message = "Email should be valid")
    @field:NotNull(message = "Email can't be empty")
    val email: String,
)
