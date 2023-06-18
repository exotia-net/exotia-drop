package net.exotia.plugins.drop.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HandlerInvalid implements InvalidUsageHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        UtilMessage.sendMessage(sender, configurationMessage.getCommandsInvalid().getInvalid());
        UtilMessage.playSound((Player) sender, configurationMessage.getSounds().getError());
    }
}
