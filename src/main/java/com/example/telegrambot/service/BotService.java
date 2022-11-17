package com.example.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    static final String BOT_TOKEN = "5798304539:AAFgpy_ost1nI7yR7BCTnlhx9cdfkGU8qxU";
    // GENERAL GROUP CHAT
    static final long GROUP_ID = -1001561970415L;
    // TESTING GROUP CHAT
    static final long TEST_GROUP_ID = -1001803996079L;
    private final TelegramBot bot;

    public BotService() {
        this.bot = new TelegramBot(BOT_TOKEN);
    }

    public TelegramBot getBot() {
        return bot;
    }

    public void sendMessage(String message) {
        // Creating response message
        SendMessage request = new SendMessage(TEST_GROUP_ID, message)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);

        // Executing request (sending message)
        bot.execute(request);
    }
}
