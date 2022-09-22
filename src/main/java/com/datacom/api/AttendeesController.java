package com.datacom.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Controller
@RequestMapping(path = "/attendees")
public class AttendeesController {

    @Autowired
    private AttendeesRepository attendeesRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attendee> postAtendee(@RequestBody Attendee attendee, HttpServletRequest request) throws IOException {

        System.out.println("JSON: " + new String(request.getInputStream().readAllBytes()));

        Optional<Attendee> tempAttendee = this.attendeesRepository.findByEmail(attendee.getEmail());
        if (tempAttendee.isPresent()) {
            if (attendee.getId() == null ||
                attendee.getId().trim().length() == 0 ||
                tempAttendee.get().getId().equals(attendee.getId()) == false)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        if (attendee.getId() == null || attendee.getId().trim().length() == 0)  {
            attendee.setId(UUID.randomUUID().toString());
            this.attendeesRepository.save(attendee);
            return ResponseEntity.created(URI.create(request.getRequestURL().append(String.format("/%s", attendee.getId())).toString())).build();
        } else {
            this.attendeesRepository.save(attendee);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attendee> getAtendeeById(@PathVariable String id) {

        Optional<Attendee> attendee = this.attendeesRepository.findById(id);

        return attendee.isEmpty() ?
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(attendee.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> getAllAtendees(@RequestParam(name = "email", required = false) String email) {

        List<Attendee> result = new ArrayList<>();

        if (email != null && email.trim().length() > 0) {
            Optional<Attendee> attendee = this.attendeesRepository.findByEmail(email);
            if (attendee.isPresent()) {
                result.add(attendee.get());
            }
        } else {
            this.attendeesRepository.findAll().forEach(result::add);
        }

        return ResponseEntity.ok(result);
    }
}
