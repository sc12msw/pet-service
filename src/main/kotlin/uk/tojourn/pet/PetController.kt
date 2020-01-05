package uk.tojourn.pet

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uk.tojourn.generic.PetServiceController
import java.text.SimpleDateFormat
import java.util.*

@RestController
class PetController : PetServiceController () {

    @GetMapping("/pets")
    fun petInfo(@RequestParam(value = "name", defaultValue = "Oscar") name: String) : Animal {
        val df = SimpleDateFormat("dd-MM-yyyy hh:mm")
        df.timeZone = TimeZone.getTimeZone("UTC")

        val toParse = "30-08-2017 02:30"
        val date = df.parse(toParse)
        //val mapper = ObjectMapper()
        logger.info("The nollo has been called")
        val dob = date
           return Animal("Cat", "Ragdoll", name, dob)
    }

}