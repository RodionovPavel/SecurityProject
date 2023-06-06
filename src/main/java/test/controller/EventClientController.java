package test.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.EventResponse;
import test.model.User;
import test.service.EventService;

import java.util.UUID;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class EventClientController {

    private final EventService eventService;

    @SecurityRequirement(name = "JWT")
    @PostMapping("/event/{drawId}/register")
    public ResponseEntity<EventResponse> getEvents(@PathVariable UUID drawId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)authentication.getPrincipal();
        return ResponseEntity.ok(eventService.registerUserInEvent(drawId, user));
    }
}
