package dev.breydan.skyfall;

import dev.breydan.skyfall.command.StartGameCommand;
import dev.breydan.skyfall.command.StopGameCommand;
import me.idriz.oss.commands.CommandRegistrar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyfall extends JavaPlugin implements Listener {

    private static Skyfall instance;

    public static Skyfall getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        CommandRegistrar registrar = new CommandRegistrar(this);
        registrar.registerCommand(new StartGameCommand());
        registrar.registerCommand(new StopGameCommand());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Bukkit.getScheduler().cancelTasks(Skyfall.getInstance());
        event.getEntity().sendMessage(ChatColor.RED + "You died, so the game ended.");
    }

    @Override
    public void onDisable() {
    }
}
