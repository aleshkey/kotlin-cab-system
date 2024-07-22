package org.modsen.passengerservice.constants

object MessageConstants {

    fun FIELD_NOT_FOUND(field: String): String = "There is no field '${field}'"

    val GENERAL_MESSAGE: String = "Something went wrong!"

    fun EMAIL_ALREADY_EXIST(email: String): String = "Email $email already registered!"

    fun PASSENGER_NOT_FOUND(id: Long) = "Cant find passenger with id: $id"
}
