package org.modsen.passengerservice.payload.general

import org.springframework.http.HttpStatus
import java.util.Date

data class ErrorResponse(
    val code: Int,
    val status: HttpStatus,
    val message: String,
    val timestamp: Date,
)
