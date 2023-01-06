package net.hezyfilive;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.User;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import static net.hezyfilive.Main.client;

public class command {
    long applicationId = client.getRestClient().getApplicationId().block();
    long guildId = 726366324942438460L;
    ApplicationCommandRequest greetCmdRequest = ApplicationCommandRequest.builder()
            .name("dota")
            .description("Укажет путь в жизни")
            .addOption(ApplicationCommandOptionData.builder()
                    .name("who")
                    .description("Ищем стратега")
                    .type(ApplicationCommandOption.Type.USER.getValue())
                    .required(false)
                    .build())
            .build();

    public command() {
        client.getRestClient().getApplicationService()
                .createGuildApplicationCommand(applicationId, guildId, greetCmdRequest)
                .subscribe();
    }
    public EmbedCreateSpec respone(ChatInputInteractionEvent event ){
        EmbedCreateSpec.Builder embed = EmbedCreateSpec.builder()
                .color(Color.ENDEAVOUR)
                .timestamp(Instant.now());
        if(event.getOption("who").isPresent()){

            Mono<User> user = event.getOption("who").flatMap(ApplicationCommandInteractionOption::getValue).map(ApplicationCommandInteractionOptionValue::asUser)
                    .get();
            user.subscribe(s ->{
                embed.description(s.getUsername() + " сегодня :");
            }).dispose();
        }



        final randomtable tab = new randomtable()
                .add("Фидить", 49)
                .add("Ступор мозговины", 49)
                .add("Фармить 1 слот", 1)
                .add("Фармить до падения трона", 1)
                .add("Нахуй закуп, это для хохлов", 1)
                .build();
        try {
            String randomItem = tab.getRandomItem();
            embed.title(randomItem);
            if(Objects.equals(randomItem, "Ступор мозговины")){
                embed.image("https://cdn.discordapp.com/attachments/997226481375785073/1061002476595195985/image.png");
            }
            else if(Objects.equals(randomItem, "Фидить")) embed.image("https://cdn.discordapp.com/attachments/997226481375785073/1061002671445782558/maxresdefault.jpg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return embed.build();

    }
}
