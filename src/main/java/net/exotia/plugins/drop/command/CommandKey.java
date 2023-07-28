package net.exotia.plugins.drop.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import io.th0rgal.oraxen.api.OraxenItems;
import net.exotia.plugins.drop.configuration.ConfigurationDrop;
import net.exotia.plugins.drop.configuration.ConfigurationFactory;
import net.exotia.plugins.drop.configuration.ConfigurationGui;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@Route(name = "key", aliases = {"klucz"})
@Permission("exotia.drop.command.admin")
public class CommandKey {
    @Inject
    private ConfigurationFactory configurationFactory;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private ConfigurationDrop configurationDrop;

    @Execute(route = "media")
    public void createKeyMedia(CommandSender sender) {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if ((OraxenItems.getIdByItem(item).equals("crate_key_fragment_media")) && item.getAmount() < 9)
            return;
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key give " + player.getDisplayName() + " media 1");
        if (item.getAmount() == 9) inventory.setItem(inventory.getHeldItemSlot(), new ItemStack(Material.AIR));
        item.setAmount(item.getAmount() - 9);
        inventory.setItem(inventory.getHeldItemSlot(), item);
    }

    @Execute(route = "memory")
    public void createKeyMemory(CommandSender sender) {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if ((OraxenItems.getIdByItem(item).equals("crate_key_fragment_memory")) && item.getAmount() < 9)
            return;
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key give " + player.getDisplayName() + " memory 1");
        if (item.getAmount() == 9) inventory.setItem(inventory.getHeldItemSlot(), new ItemStack(Material.AIR));
        item.setAmount(item.getAmount() - 9);
        inventory.setItem(inventory.getHeldItemSlot(), item);
    }
}
