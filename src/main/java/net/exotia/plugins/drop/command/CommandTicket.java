package net.exotia.plugins.drop.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.By;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import io.th0rgal.oraxen.api.OraxenItems;
import net.exotia.plugins.drop.command.argument.ArgumentTicket;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.configuration.ConfigurationPlugin;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@Route(name = "ticket", aliases = {"bilet"})
@Permission("exotia.drop.command.ticket")
public class CommandTicket {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;

    @Execute(required = 1)
    public void sendPlayer(Player player, @Arg @By(ArgumentTicket.KEY) String ticketName) {
        if (!player.hasPermission("exotia.ticket." + ticketName)) return;
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if (!OraxenItems.getIdByItem(item).equals("ticket_" + ticketName)) {
            UtilMessage.sendMessage(player, configurationMessage.getCommandsTicket().getInvalid());
            return;
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), configurationPlugin.getTicketCommand().replace("%value_1%", player.getDisplayName()).replace("%value_2%", ticketName));
        inventory.setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
        UtilMessage.sendMessage(player, configurationMessage.getCommandsTicket().getSuccess());
    }
}
