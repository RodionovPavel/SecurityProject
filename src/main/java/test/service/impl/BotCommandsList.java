package test.service.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import test.model.BotCommands;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotCommandsList {
    private List<BotCommand> listOfCommands;

    public BotCommandsList() {
        this.listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand(BotCommands.RANDOM.getCommand(), BotCommands.RANDOM.getDescription()));
        listOfCommands.add(new BotCommand(BotCommands.REGISTER.getCommand(), BotCommands.REGISTER.getDescription()));
        listOfCommands.add(new BotCommand(BotCommands.TOP.getCommand(), BotCommands.TOP.getDescription()));
        listOfCommands.add(new BotCommand(BotCommands.SCORE.getCommand(), BotCommands.SCORE.getDescription()));
        listOfCommands.add(new BotCommand(BotCommands.HELP.getCommand(), BotCommands.HELP.getDescription()));
    }

    public List<BotCommand> getListOfCommands() {
        return listOfCommands;
    }
}

