package test.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.EventRequest;
import test.dto.EventResponse;
import test.service.EventService;

import java.util.List;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class EventController {

    private final EventService eventService;

    @SecurityRequirement(name = "JWT")
    @PostMapping("/event")
    public ResponseEntity<String> createEvent(@RequestBody EventRequest eventRequest) {
        log.info("Create event title: '{}'", eventRequest.getTitle());
        eventService.createEvent(eventRequest);
        return ResponseEntity.ok("ok");
    }

}
