package net.exotia.plugins.drop.drop.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.drop.configuration.ConfigurationDrop;
import net.exotia.plugins.drop.configuration.ConfigurationGui;
import net.exotia.plugins.drop.configuration.ConfigurationMessage;
import net.exotia.plugins.drop.configuration.section.SectionDrop;
import net.exotia.plugins.drop.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class GuiDrop {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private ConfigurationDrop configurationDrop;

    public void setupGui(BaseGui gui, List<Integer> emptySlots) {
        GuiItem filling = ItemBuilder.from(Material.AIR).asGuiItem();
        gui.setItem(emptySlots, filling);
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> UtilMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
    }

    public void open(Player player) {
        SectionDrop guiDrop = configurationGui.getGuiDrop();
        Gui gui = Gui.gui().rows(guiDrop.getSize()).title(UtilMessage.getComponent(guiDrop.getTitle())).create();
        setupGui(gui, guiDrop.getSlotsEmpty());
        gui.open(player);
    }
}
