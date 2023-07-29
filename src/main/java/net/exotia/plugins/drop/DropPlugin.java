package net.exotia.plugins.drop;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import lombok.Getter;
import net.exotia.plugins.drop.command.CommandKey;
import net.exotia.plugins.drop.command.CommandReload;
import net.exotia.plugins.drop.command.CommandTicket;
import net.exotia.plugins.drop.configuration.*;
import net.exotia.plugins.drop.drop.gui.GuiDrop;
import net.exotia.plugins.drop.handler.HandlerInvalid;
import net.exotia.plugins.drop.handler.HandlerUnauthorized;
import net.exotia.plugins.drop.listener.ListenerBlock;
import net.exotia.plugins.drop.utils.UtilMessage;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class DropPlugin extends JavaPlugin {
    @Getter
    private static Plugin plugin;
    @Getter
    private static BukkitAudiences audiences;
    private final Injector injector = OkaeriInjector.create();
    private final ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
    private ConfigurationPlugin configurationPlugin;
    private ConfigurationMessage configurationMessage;
    private ConfigurationGui configurationGui;
    private ConfigurationDrop configurationDrop;

    @Override
    public void onEnable() {
        plugin = this;
        audiences = BukkitAudiences.create(this);

        injector.registerInjectable(plugin);
        injector.registerInjectable(injector);
        injector.registerInjectable(configurationFactory);

        setupConfiguration();
        setupUtils();
        setupCommands();
        setupEvents();
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        configurationPlugin = configurationFactory.produce(ConfigurationPlugin.class, "plugin.yml");
        configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
        configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");
        configurationDrop = configurationFactory.produce(ConfigurationDrop.class, "drops.yml");

        injector.registerInjectable(configurationPlugin);
        injector.registerInjectable(configurationMessage);
        injector.registerInjectable(configurationGui);
        injector.registerInjectable(configurationDrop);
    }

    private void setupUtils() {
        injector.registerInjectable(injector.createInstance(GuiDrop.class));
    }

    private void setupCommands() {
        LiteBukkitFactory.builder(this.getServer(), "exotia.net")
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), UtilMessage.getMessage(configurationMessage.getCommandsPlayer().getOffline())))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>(UtilMessage.getMessage(configurationMessage.getCommandsPlayer().getOnly())))
                .commandInstance(
                        injector.createInstance(CommandReload.class),
                        injector.createInstance(CommandTicket.class),
                        injector.createInstance(CommandKey.class)
                )
                .invalidUsageHandler(injector.createInstance(HandlerInvalid.class))
                .permissionHandler(injector.createInstance(HandlerUnauthorized.class))
                .register();
    }

    private void setupEvents() {
        Stream.of(
                injector.createInstance(ListenerBlock.class)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void cleanUp() {
        configurationPlugin.save();
        configurationMessage.save();
        configurationGui.save();
        configurationDrop.save();
    }
}