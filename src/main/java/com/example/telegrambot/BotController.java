package com.example.telegrambot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        return "<p><a href=\"/update\">Enviar mensaje</a></p>\n" +
                "<form action=\"/winners\" method=\"POST\">\n" +
                "  <label for=\"nombre\">Nombre: <input type=\"text\" name=\"nombre\" id=\"nombre\"></label>\n" +
                "  <label for=\"apellidos\">Apellidos: <input type=\"text\" name=\"apellidos\" id=\"apellidos\"></label>\n" +
                "  <input type=\"submit\" value=\"Enviar\">\n" +
                "</form>" +
                "<p><a href=\"/winners\">Ver ganadores</a></p>";
    }

    @GetMapping("test")
    public RedirectView testMessage() {
        // TODO: Get JSON and Parse to get winner(s)

        // Sending message
        bot.sendMessage("Heyyy muy buenas a todos");

        // Redirect to index
        return new RedirectView("");
    }

    @PostMapping("winners")
    public void sendWinner(@RequestBody String jsonResponse) throws IOException {
        if (!isTeamDataModified(jsonResponse)) {
            return;
        }

        String json = getTeams();
        TeamData data = new Gson().fromJson(json, TeamData.class);
        List<Team> winners = data.getWinners();
        StringBuilder result = new StringBuilder();
        result.append("Lista de ganadores actualizada\n\nRanking:\n");
        for (int i = 0; i < winners.size(); i++) {
            result.append(i + 1 ).append(". ").append(winners.get(i)).append("\n");
        }

        bot.sendMessage(result.toString());
    }

    // Checks if teamdata has been modified
    private boolean isTeamDataModified(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject object = jsonObject.optJSONObject("head_commit");
        JSONArray jsonArray = object.getJSONArray("modified");
        for(int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getString(i).contains("teamdata.json")) {
                return true;
            }
        }
        return false;
    }

    // Read teamdata from repository
    private String getTeams() throws IOException {
        // Instantiating the URL class
        URL url = new URL("https://raw.githubusercontent.com/Bugabit/bootcampsolera/main/src/data/teamdata.json");

        // Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());

        // Instantiating the StringBuilder class to hold the result
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.next()).append(" ");
        }

        // Retrieving the String from the String Buffer object
        return sb.toString();
    }
}
