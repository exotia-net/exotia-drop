package net.exotia.plugins.drop.drop.rewards;

import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.utils.UtilItem;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum DropType {
    ITEM {
        @Override
        public void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage) {
            ItemStack item = UtilItem.getItem(drop);
            player.getInventory().addItem(item);
            UtilMessage.sendMessage(player, configurationMessage.getEventsBreak().getDropItem(), String.valueOf(item.getAmount()), drop.getDisplayName());
            UtilMessage.playSound(player, configurationMessage.getSounds().getSuccess());
        }
    },
    LEVEL {
        @Override
        public void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage) {
            int level = drop.getDropAmount();
            player.setLevel(player.getLevel() + level);
            UtilMessage.sendMessage(player, configurationMessage.getEventsBreak().getDropLevel(), String.valueOf(level));
            UtilMessage.playSound(player, configurationMessage.getSounds().getSuccess());
        }
    },
    COMMAND {
        @Override
        public void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage) {
            if (drop.getId().contains("%player_name%"))
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), drop.getId().replace("%player_name%", player.getDisplayName()));
            else Bukkit.getServer().dispatchCommand(player, drop.getId());
            UtilMessage.sendMessage(player, configurationMessage.getEventsBreak().getDropCommand(), drop.getDisplayName());
            UtilMessage.playSound(player, configurationMessage.getSounds().getSuccess());
        }
    };

    public abstract void dropPlayer(Player player, Drop drop, ConfigurationMessage configurationMessage);
}