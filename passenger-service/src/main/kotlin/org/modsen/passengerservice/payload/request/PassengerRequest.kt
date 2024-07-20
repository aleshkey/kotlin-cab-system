package org.modsen.passengerservice.payload.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PassengerRequest(
        val id: Long?,

        @NotBlank
        val firstName: String = "",

        @NotBlank
        val lastName: String = "",

        @Email
        val email: String = ""
)
