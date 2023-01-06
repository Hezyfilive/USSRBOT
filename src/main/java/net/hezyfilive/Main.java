package net.hezyfilive;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;

public class Main {
    static GatewayDiscordClient client;
    public static void main(String[] args) {
        client = DiscordClientBuilder.create(args[0]).build()
                .login()
                .block();
        command command = new command();


        assert client != null;
        client.on(ChatInputInteractionEvent.class, event -> {
            if (event.getCommandName().equals("dota")) {
                return event.reply().withEmbeds(command.respone(event));
            }
            ;
            return null;
        }).subscribe();

        client.onDisconnect().block();

    }
}