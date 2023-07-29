package net.exotia.plugins.drop.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.By;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import io.th0rgal.oraxen.api.OraxenItems;
import net.exotia.plugins.drop.command.argument.ArgumentKey;
import net.exotia.plugins.drop.configuration.*;
import net.exotia.plugins.drop.utils.UtilMessage;
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
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;

    @Execute(required = 1)
    public void createKey(Player player, @Arg @By(ArgumentKey.KEY) String keyName) {
        if (!player.hasPermission("exotia.ticket." + keyName)) return;
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if (!OraxenItems.getIdByItem(item).equals("crate_key_fragment_" + keyName) || item.getAmount() < 9) {
            UtilMessage.sendMessage(player, configurationMessage.getCommandsKey().getInvalid());
            return;
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), configurationPlugin.getKeysCommand().replace("%value_1%", player.getDisplayName().replace("%value_2%", keyName)));
        if (item.getAmount() == 9) {
            inventory.setItem(inventory.getHeldItemSlot(), new ItemStack(Material.AIR));
            UtilMessage.sendMessage(player, configurationMessage.getCommandsKey().getSuccess());
            return;
        }
        item.setAmount(item.getAmount() - 9);
        inventory.setItem(inventory.getHeldItemSlot(), item);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsKey().getSuccess());
    }
}
