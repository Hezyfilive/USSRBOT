package net.hezyfilive;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;

public class test {
    ApplicationCommandRequest greetCmdRequest = ApplicationCommandRequest.builder()
            .name("dota")
            .description("Укажет путь в жизни")
            .build();
}
