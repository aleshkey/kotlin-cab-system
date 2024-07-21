package org.modsen.passengerservice.payload.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PassengerRequest(
    val id: Long?,

    @field:NotBlank(message = "First name can't be empty")
    val firstName: String?,

    @field:NotBlank(message = "Last name can't be empty")
    val lastName: String?,

    @field:Email(message = "Email should be valid")
    @field:NotBlank(message = "Email can't be empty")
    val email: String?
)
