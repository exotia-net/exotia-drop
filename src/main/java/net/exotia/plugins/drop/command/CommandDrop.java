package net.exotia.plugins.drop.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.drop.drop.gui.GuiDrop;
import org.bukkit.entity.Player;

@Route(name = "drop", aliases = {"dropy"})
@Permission("exotia.drop.command.drop")
public class CommandDrop {
    @Inject
    private GuiDrop guiDrop;

    @Execute
    public void openGUI(Player player) {
        guiDrop.open(player);
    }
}
