package uk.tojourn.generic

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

abstract class PetServiceController {

    var logger: Logger = LoggerFactory.getLogger(PetServiceController::class.java)

    @ExceptionHandler(Throwable::class)
    @ResponseBody
    fun onGenericException(ex: Throwable): ResponseEntity<PetServiceResponse> {
        logger.error(ex.cause.toString())
        return when (ex.javaClass) {
            HttpMessageConversionException::class.java -> ResponseEntity(PetServiceResponse("Bad request, the payload was valid json but did not contain valid attributes", 4000), HttpStatus.BAD_REQUEST)
            HttpMessageNotReadableException::class.java -> ResponseEntity(PetServiceResponse("Bad request, the payload was not valid json", 4001), HttpStatus.BAD_REQUEST)
            else -> {
                ResponseEntity(PetServiceResponse("Unknown", 5000), HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }
}