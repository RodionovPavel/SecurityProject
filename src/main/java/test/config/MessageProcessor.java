package test.config;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageProcessor {

    SendMessage execute(Long chatId, Message message);

    boolean isSupported(String commands);


}
