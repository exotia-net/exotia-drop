package net.exotia.plugins.drop.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationPlugin extends OkaeriConfig {
    @Comment("Ticket list")
    private HashMap<String, String> tickets = setupTicket();

    @Comment("Ticket command")
    private String ticketCommand = "rtp player %value_1% %value_2%";

    private List<String> keyNames = List.of("media", "memory");

    @Comment("Key command")
    private String keysCommand = "crate key give %value_1% %value_2%";

    private HashMap<String, String> setupTicket() {
        tickets = new HashMap<>();
        tickets.put("world_nether", "Piekło");
        tickets.put("world_the_end", "Kres");
        return tickets;
    }
}
