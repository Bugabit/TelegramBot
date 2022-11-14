package com.example.telegrambot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class BotController {

    private final BotService bot;

    @Autowired
    public BotController(BotService bot) {
        this.bot = bot;
    }

    @GetMapping
    public String index() {
        return  "<p><a href=\"/update\">Enviar mensaje</a></p>\n" +
                "<form action=\"/winner\" method=\"POST\">\n" +
                "  <label for=\"nombre\">Nombre: <input type=\"text\" name=\"nombre\" id=\"nombre\"></label>\n" +
                "  <label for=\"apellidos\">Apellidos: <input type=\"text\" name=\"apellidos\" id=\"apellidos\"></label>\n" +
                "  <input type=\"submit\" value=\"Enviar\">\n" +
                "</form>";
    }

    @GetMapping("test")
    public RedirectView testMessage() {
        // TODO: Get JSON and Parse to get winner(s)

        // Sending message
        bot.sendMessage("Heyyy muy buenas a todos");

        // Redirect to index
        return new RedirectView("");
    }

    @PostMapping("winner")
    public String sendWinner(@RequestBody String body) {
        return body;
    }
}
