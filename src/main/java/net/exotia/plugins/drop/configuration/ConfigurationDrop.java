package net.exotia.plugins.drop.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.drop.drop.rewards.Drop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class ConfigurationDrop extends OkaeriConfig {
    @Comment("Multiplier for amount")
    private int multiplier = 1;
    @Comment("Drop list")
    private List<Drop> drop = setupDrop();

    public List<Drop> setupDrop() {
        List<Drop> drop = new ArrayList<>();
        drop.add(Drop.builder()
                .dropType("item")
                .id("food_fruit_kiwi")
                .itemType("oraxen")
                .displayName("Kiwi")
                .amount(16)
                .chance(25)
                .build()
        );
        drop.add(Drop.builder()
                .dropType("level")
                .amount(4)
                .chance(25)
                .build()
        );
        drop.add(Drop.builder()
                .dropType("command")
                .displayName("Niespodzianka")
                .id("/say Brah")
                .chance(25)
                .build()
        );
        return drop;
    }

    public Drop getRandomDrop() {
        return drop.get(new Random().nextInt(drop.size()));
    }
}
