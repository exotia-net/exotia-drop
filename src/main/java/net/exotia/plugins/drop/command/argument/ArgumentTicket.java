package net.exotia.plugins.drop.command.argument;

import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.configuration.ConfigurationPlugin;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.entity.Player;
import panda.std.Option;
import panda.std.Result;

import java.util.List;
import java.util.stream.Collectors;

public class ArgumentTicket implements OneArgument<String> {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    public static final String KEY = "ticket_name";

    @Override
    public Result<String, ?> parse(LiteInvocation liteInvocation, String s) {
        String argument = configurationPlugin.getTickets().keySet().stream().filter(string -> string.equals(s)).findFirst().orElse(null);
        if (argument == null) UtilMessage.playSound((Player) liteInvocation.sender().getHandle(), configurationMessage.getSounds().getError());
        return Option.of(argument).toResult(UtilMessage.getMessage(configurationMessage.getCommandsTicket().getNotFound()));
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        List<String> ticketList = configurationPlugin.getTickets().keySet().stream().toList();
        return ticketList.stream()
                .filter(string -> invocation.sender().hasPermission("exotia.ticket." + string))
                .map(Suggestion::of)
                .collect(Collectors.toList());
    }
}