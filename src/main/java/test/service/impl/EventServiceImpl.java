package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dto.ClientResponse;
import test.dto.EventRequest;
import test.dto.EventResponse;
import test.dto.PrizeRequest;
import test.dto.PrizeResponse;
import test.exsap.CustomException;
import test.model.Draw;
import test.model.User;
import test.model.WinPrize;
import test.repository.DrawRepository;
import test.repository.WinPrizeRepository;
import test.service.EventService;
import test.service.UserComponent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final DrawRepository drawRepository;
    private final WinPrizeRepository winPrizeRepository;
    private final UserComponent userComponent;

    @Override
    @Transactional
    public void createEvent(EventRequest eventRequest) {
        var draw = createDraw(eventRequest);
        var prizes = eventRequest.getPrizes().stream().map(win -> createWinPrize(win, draw)).toList();

    }

    @Override
    public List<EventResponse> getAllEvents() {
        var draw = drawRepository.findAll();
        return draw.stream().map(this::createEventWinners).collect(Collectors.toList());
    }

    @Override
    public EventResponse registerUserInEvent(UUID drawId, User user) {
        var draw = getDrawById(drawId);
        var clients = draw.getClients();
        clients.add(user);
        draw.setClients(clients);
        user.getDraws().add(draw);
        userComponent.update(user);
        return createEventWinners(drawRepository.save(draw));
    }

    @Override
    public List<Draw> getDrawsForPlay() {
        return drawRepository.getAllByDrawDateBefore(LocalDateTime.now());
    }

    @Override
    public List<WinPrize> updateAll(List<WinPrize> winPrizes) {
        return winPrizeRepository.saveAll(winPrizes);
    }

    private Draw createDraw(EventRequest eventRequest) {
        var draw = Draw.builder()
                .drawDate(eventRequest.getDrawDate())
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .imagePreview(eventRequest.getImagePreview()).build();
        return drawRepository.save(draw);
    }

    private WinPrize createWinPrize(PrizeRequest prizeRequest, Draw draw) {
        var prize = WinPrize.builder()
                .title(prizeRequest.getTitle())
                .description(prizeRequest.getDescription())
                .place(prizeRequest.getPlace())
                .draw(draw).build();
        return winPrizeRepository.save(prize);
    }

    private EventResponse createEventWinners(Draw draw) {
        return EventResponse.builder()
                .id(draw.getId())
                .title(draw.getTitle())
                .description(draw.getDescription())
                .drawDate(draw.getDrawDate())
                .imagePreview(draw.getImagePreview())
                .clients(Objects.nonNull(draw.getClients()) ?
                        draw.getClients().stream().map(this::createClientResponse).collect(Collectors.toList()) : null)
                .prizes(draw.getPrizes().stream().map(this::createPrizeWin).collect(Collectors.toList())).build();
    }

    private PrizeResponse createPrizeWin(WinPrize winPrize) {
        return PrizeResponse.builder()
                .id(winPrize.getId())
                .title(winPrize.getTitle())
                .description(winPrize.getDescription())
                .place(winPrize.getPlace())
                .winnerId(Objects.nonNull(winPrize.getClient()) ? winPrize.getClient().getId() : null)
                .build();
    }

    private Draw getDrawById(UUID drawId) {
        var draw = drawRepository.findById(drawId);
        if (draw.isEmpty()) {
            throw new CustomException("Мероприятие не найдено");
        }
        return draw.get();
    }

    private ClientResponse createClientResponse(User user) {
        return ClientResponse.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .login(user.getLogin()).build();
    }

}
