package net.exotia.plugins.drop.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.drop.configuration.ConfigurationDrop;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.drop.rewards.Drop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ListenerBlock implements Listener {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationDrop configurationDrop;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Drop drop = configurationDrop.getRandomDrop();
        if (drop.canDrop()) return;
        drop.dropPlayer(player, configurationMessage);
    }
}
