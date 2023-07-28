package net.exotia.plugins.drop.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.drop.drop.rewards.Drop;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Getter
public class ConfigurationDrop extends OkaeriConfig {
    @Comment("Multiplier for amount")
    private float multiplier = 1;
    @Comment("Drop-able block")
    private HashMap<String, List<String>> blocks = setupBlocks();
    @Comment("Drop list")
    private HashMap<String, List<Drop>> drops = setupDrop();

    public HashMap<String, List<Drop>> setupDrop() {
        HashMap<String, List<Drop>> drop = new HashMap<>();
        drop.put("stone", List.of(
                Drop.builder()
                        .dropType("item")
                        .id("crate_key_fragment_memory")
                        .itemType("oraxen")
                        .displayName("<gradient:#F4DF52:#E8AA2D><bold>Fragment Pamiętnego Klucza</bold></gradient>")
                        .amount(1)
                        .chance(0.25f)
                        .build())
        );
        drop.put("leaves_jungle", List.of(
                Drop.builder()
                        .dropType("item")
                        .id("food_fruit_banana")
                        .itemType("oraxen")
                        .displayName("Banan")
                        .amount(2)
                        .chance(2.0f)
                        .build())
        );
        drop.put("leaves_cherry", List.of(
                Drop.builder()
                        .dropType("item")
                        .id("food_fruit_cherry")
                        .itemType("oraxen")
                        .displayName("Wiśnie")
                        .amount(2)
                        .chance(2.0f)
                        .build())
        );
        return drop;
    }

    public HashMap<String, List<String>> setupBlocks() {
        HashMap<String, List<String>> blocks = new HashMap<>();
        blocks.put("stone", List.of("stone", "deepslate", "diorite", "andesite", "granite", "tuff", "sandstone", "red_sandstone"));
        blocks.put("wood", List.of("oak_log", "birch_log", "spruce_log", "jungle_log", "acacia_log", "dark_oak_log", "mangrove_log"));
        blocks.put("leaves_normal", List.of("oak_leaves", "birch_leaves", "spruce_leaves", "acacia_leaves", "dark_oak_leaves", "mangrove_leaves"));
        blocks.put("leaves_jungle", List.of("jungle_leaves"));
        blocks.put("leaves_cherry", List.of("cherry_leaves"));
        return blocks;
    }

    public String getDropType(Material minedBlock) {
        for (String block : blocks.keySet()) {
            if (blocks.get(block).contains(minedBlock.name().toLowerCase())) return block;
        }
        return null;
    }

    public Drop getDrop(String dropType) {
        List<Drop> dropList = drops.get(dropType);
        return dropList.get(new Random().nextInt(dropList.size()));
    }
}
