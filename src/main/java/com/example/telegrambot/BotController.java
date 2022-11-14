package com.example.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class BotController {

    static final String BOT_TOKEN = "5798304539:AAFgpy_ost1nI7yR7BCTnlhx9cdfkGU8qxU";
    static final long CHAT_ID = 1636051587L;
    static final long GROUP_ID = -1001561970415L;
    static final long TEST_GROUP_ID = -860576555L;

    @GetMapping
    public String index() {
        return "<p><a href=\"/update\">Enviar mensaje</a></p>";
    }

    @GetMapping("update")
    public RedirectView sendMessage() {
        TelegramBot bot = new TelegramBot(BOT_TOKEN);

        // TODO: Get JSON and Parse to get winner(s)

        // Creating response message
        SendMessage request = new SendMessage(TEST_GROUP_ID, "Mensaje de prueba desde Spring")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true)
                .replyMarkup(new ForceReply());

        // Executing request (sending message)
        bot.execute(request);

        // Redirect to index
        return new RedirectView("");
    }

}
