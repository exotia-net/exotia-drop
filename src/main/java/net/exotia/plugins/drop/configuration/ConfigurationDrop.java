package net.exotia.plugins.drop.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.drop.drop.rewards.Drop;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class ConfigurationDrop extends OkaeriConfig {
    @Comment("Multiplier for amount")
    private float multiplier = 1;
    @Comment("Drop-able block")
    private List<String> block = List.of("stone", "deepslate", "sand", "sandstone");
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
                .chance(2.5f)
                .build()
        );
        drop.add(Drop.builder()
                .dropType("level")
                .amount(4)
                .chance(1.5f)
                .build()
        );
        drop.add(Drop.builder()
                .dropType("command")
                .displayName("Niespodzianka")
                .id("/say Brah")
                .chance(0.25f)
                .build()
        );
        return drop;
    }

    public Drop getRandomDrop() {
        return drop.get(new Random().nextInt(drop.size()));
    }
}
