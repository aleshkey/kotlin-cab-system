package org.modsen.passengerservice.error

import org.modsen.passengerservice.constants.MessageConstants

class EmailAlreadyRegistered(
    email: String,
) : RuntimeException(MessageConstants.EMAIL_ALREADY_EXIST(email))
