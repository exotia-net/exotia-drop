package net.exotia.plugins.drop.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@Route(name = "ticket", aliases = {"bilet"})
@Permission("exotia.drop.command.ticket")
public class CommandTicket {
    @Execute(route = "nether")
    public void sendToNether(CommandSender sender) {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if (!item.isSimilar(OraxenItems.getItemById("ticket_the_nether").build())) return;
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "rtp player " + player.getDisplayName() + " world_nether");
        inventory.setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
    }

    @Execute(route = "end")
    public void sendToEnd(CommandSender sender) {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if (!item.isSimilar(OraxenItems.getItemById("ticket_the_nether").build())) return;
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "rtp player " + player.getDisplayName() + " world_the_end");
        inventory.setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
    }
}
