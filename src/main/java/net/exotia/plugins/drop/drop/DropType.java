package net.exotia.plugins.drop.drop;

import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.utils.UtilItem;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum DropType {
    ITEM {
        @Override
        public void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage, float multiplier) {
            ItemStack item = UtilItem.getItem(drop, multiplier);
            player.getInventory().addItem(item);
            if (drop.getChance() < 1) {
                UtilMessage.playSound(player, configurationMessage.getSounds().getSuccess());
                return;
            }
            UtilMessage.sendMessage(player, configurationMessage.getEventsBreak().getDropItem(), String.valueOf(item.getAmount()), drop.getDisplayName());
            UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        }
    },
    LEVEL {
        @Override
        public void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage, float multiplier) {
            int level = (int) (drop.getDropAmount() * multiplier);
            player.setLevel(player.getLevel() + level);
            if (drop.getChance() < 1) {
                UtilMessage.playSound(player, configurationMessage.getSounds().getSuccess());
                return;
            }
            UtilMessage.sendMessage(player, configurationMessage.getEventsBreak().getDropLevel(), String.valueOf(level));
            UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        }
    },
    COMMAND {
        @Override
        public void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage, float multiplier) {
            if (drop.getId().contains("%player_name%"))
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), drop.getId().replace("%player_name%", player.getDisplayName()));
            else
                Bukkit.getServer().dispatchCommand(player, drop.getId().replace("%value_1%", String.valueOf(drop.getDropAmount() * multiplier)));
            if (drop.getChance() < 1) {
                UtilMessage.playSound(player, configurationMessage.getSounds().getSuccess());
                return;
            }
            UtilMessage.sendMessage(player, configurationMessage.getEventsBreak().getDropCommand(), drop.getDisplayName());
            UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        }
    };

    public abstract void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage, float multiplier);
}