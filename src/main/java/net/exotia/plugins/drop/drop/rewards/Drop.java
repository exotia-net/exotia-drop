package net.exotia.plugins.drop.drop.rewards;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

@Builder
@Getter
public class Drop extends OkaeriConfig {
    private String dropType;
    private String id;
    private String itemType;
    private String displayName;
    private List<String> lore;
    private int amount;
    private int chance;

    public void dropPlayer(Player player, ConfigurationMessage configurationMessage) {
        DropType.valueOf(dropType.toUpperCase()).dropPlayer(player, this, configurationMessage);
    }

    public int getDropAmount() {
        return new Random().nextInt(getAmount()) + 1;
    }

    public boolean canDrop() {
        return new Random().nextInt(101) >= chance;
    }
}