package uk.tojourn.user

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.tojourn.generic.PetServiceController
import uk.tojourn.generic.PetServiceResponse
import java.util.*
import javax.validation.Valid


@RestController
class UserController : PetServiceController() {

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<PetServiceResponse> {
        val userWithId = createAndAddUUIDToUser(user)
        logger.info("Creating user: $userWithId")
        val successfullyCreatedUserMessage = "User $userWithId.id has been created"
        // * TODO add db stuff here
        logger.info(successfullyCreatedUserMessage)
        val responseHeaders = HttpHeaders()
        responseHeaders["Location"] = "/user/$userWithId.id"
        return ResponseEntity(
                PetServiceResponse(successfullyCreatedUserMessage, 2001),
                responseHeaders,
                HttpStatus.CREATED
        )
    }

    private fun createAndAddUUIDToUser (user: User) : User{
        val uuid = UUID.randomUUID()
        val randomUUIDString = uuid.toString()
        user.id = randomUUIDString
        return user
    }


}