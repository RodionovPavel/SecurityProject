package test.service;

import test.dto.EventRequest;
import test.dto.EventResponse;
import test.model.Draw;
import test.model.User;
import test.model.WinPrize;

import java.util.List;
import java.util.UUID;

public interface EventService {

    void createEvent(EventRequest eventRequest);

    List<EventResponse> getAllEvents();

    EventResponse registerUserInEvent(UUID drawId, User user);

    List<Draw> getDrawsForPlay();

    List<WinPrize> updateAll(List<WinPrize> winPrizes);
}
