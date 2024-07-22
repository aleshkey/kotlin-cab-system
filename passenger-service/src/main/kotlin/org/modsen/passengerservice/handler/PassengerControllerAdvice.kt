package org.modsen.passengerservice.handler

import org.modsen.passengerservice.constants.MessageConstants
import org.modsen.passengerservice.error.EmailAlreadyRegistered
import org.modsen.passengerservice.error.PassengerNotFoundException
import org.modsen.passengerservice.payload.general.ErrorResponse
import org.springframework.data.mapping.PropertyReferenceException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import kotlin.collections.HashMap

@RestControllerAdvice
class PassengerControllerAdvice : ResponseEntityExceptionHandler() {
    @ExceptionHandler
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    status = HttpStatus.INTERNAL_SERVER_ERROR,
                    message = MessageConstants.GENERAL_MESSAGE,
                    timestamp = Date(),
                )
            )

    @ExceptionHandler
    fun handleDataIntegrityViolationException(ex: EmailAlreadyRegistered): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(
                ErrorResponse(
                    code = HttpStatus.CONFLICT.value(),
                    status = HttpStatus.CONFLICT,
                    message = ex.message!!,
                    timestamp = Date(),
                )
            )

    @ExceptionHandler
    fun handleNotFoundException(ex: PassengerNotFoundException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.NOT_FOUND
        return ResponseEntity
            .status(status)
            .body(
                ErrorResponse(
                    code = status.value(),
                    status = status,
                    message = ex.message!!,
                    timestamp = Date(),
                )
            )
    }

    @ExceptionHandler
    fun handlePropertyReferenceException(ex: PropertyReferenceException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity
            .status(status)
            .body(
                ErrorResponse(
                    code = status.value(),
                    status = status,
                    message = MessageConstants.FIELD_NOT_FOUND(ex.propertyName),
                    timestamp = Date(),
                )
            )
    }

    override fun handleMethodArgumentNotValid (
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val statusCode = HttpStatus.BAD_REQUEST
        val errorMap = HashMap<String, String>()
        ex.bindingResult.allErrors
            .map { error ->
                val fieldError = error as FieldError
                errorMap.put(fieldError.field, fieldError.defaultMessage.toString())
            }
        return ResponseEntity
            .status(statusCode)
            .body(
                ErrorResponse(
                    code = statusCode.value(),
                    status = statusCode,
                    message = errorMap.toString(),
                    timestamp = Date(),
                ),
            )
    }

}
