package net.exotia.plugins.drop.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.drop.configuration.ConfigurationDrop;
import net.exotia.plugins.drop.configuration.ConfigurationFactory;
import net.exotia.plugins.drop.configuration.ConfigurationGui;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "drop", aliases = {"dropy"})
@Permission("exotia.drop.command.admin")
public class CommandReload {
    @Inject
    private ConfigurationFactory configurationFactory;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private ConfigurationDrop configurationDrop;

    @Execute(route = "admin reload")
    public void reload(CommandSender sender) {
        try {
            configurationMessage.load(true);
            configurationGui.load(true);
            configurationDrop.load(true);
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getSuccess());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        } catch (OkaeriException error) {
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getFailed());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
            configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");
            configurationDrop = configurationFactory.produce(ConfigurationDrop.class, "drops.yml");
        }
    }
}
