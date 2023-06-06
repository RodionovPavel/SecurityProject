package test.service.asunc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import test.model.Draw;
import test.service.EventService;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class EventScheduler {

    private final EventService eventService;

    @Scheduled(fixedDelayString = "PT1M")
    public void winDraws() {
        log.info("Start play draw");
        var draws = eventService.getDrawsForPlay();
        draws.forEach(this::setWinners);
    }

    private void setWinners(Draw draw) {
        var clients = new ArrayList<>(draw.getClients());

        if (clients.isEmpty()) {
            return;
        }
        var prize = new ArrayList<>(draw.getPrizes());

        prize.forEach(p -> {
            p.setClient(clients.get(generatePosition(clients.size())));
        });
        eventService.updateAll(prize);
    }


    public int generatePosition(int size) {
        Random rnd = new Random();
        return rnd.nextInt(size);
    }
}
