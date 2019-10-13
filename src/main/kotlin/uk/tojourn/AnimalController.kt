package uk.tojourn

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.util.TimeZone
import java.text.SimpleDateFormat




@RestController
class AnimalController {

    @GetMapping("/pet")
    fun petInfo(@RequestParam(value = "name", defaultValue = "Oscar") name: String) : Animal{
        val df = SimpleDateFormat("dd-MM-yyyy hh:mm")
        df.timeZone = TimeZone.getTimeZone("UTC")

        val toParse = "30-08-2017 02:30"
        val date = df.parse(toParse)

        val mapper = ObjectMapper()
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//        // StdDateFormat is ISO8601 since jackson 2.9
//        mapper.dateFormat = StdDateFormat().withColonInTimeZone(true)
//        val result = mapper.writeValueAsString(event)
        val dob = date
           return Animal("Cat", "Ragdoll" , name, dob)
    }

}