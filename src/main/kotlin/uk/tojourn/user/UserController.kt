package uk.tojourn.user

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.tojourn.generic.PetServiceController
import uk.tojourn.generic.PetServiceResponse
import java.util.*
import javax.validation.Valid


@RestController
class UserController : PetServiceController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<PetServiceResponse> {
        val userWithId = createAndAddUUIDToUser(user)
        val userId = userWithId.id
        logger.info("Creating user $userId in database")
        userRepository.save(user)
        val successfullyCreatedUserMessage = "User $userId has been created"
        logger.info(successfullyCreatedUserMessage)
        val responseHeaders = HttpHeaders()
        responseHeaders["Location"] = "/user/$userId"
        return ResponseEntity(
                PetServiceResponse(successfullyCreatedUserMessage, 2001),
                responseHeaders,
                HttpStatus.CREATED
        )
    }


    @GetMapping("/user")
    fun getUsers() :  ResponseEntity<PetServiceResponse> {
        val users = userRepository.findAll()
        val gson = Gson()
        val jsonString = gson.toJson(users)
        return ResponseEntity(
                PetServiceResponse(jsonString, 2000),
                HttpStatus.OK
        )
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable(value="id") id: String) :  ResponseEntity<PetServiceResponse> {
        val user = userRepository.findById(id)
        if (!user.isPresent){
            return ResponseEntity(
                    PetServiceResponse("Not Found", 4004),
                    HttpStatus.NOT_FOUND
            )
        }
        val gson = Gson()
        val jsonString = gson.toJson(user)
        return ResponseEntity(
                PetServiceResponse(jsonString, 2000),
                HttpStatus.OK
        )
    }


    private fun createAndAddUUIDToUser (user: User) : User{
        val uuid = UUID.randomUUID()
        val randomUUIDString = uuid.toString()
        user.id = randomUUIDString
        return user
    }


}