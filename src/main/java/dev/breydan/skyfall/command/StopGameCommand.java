package dev.breydan.skyfall.command;

import dev.breydan.skyfall.Skyfall;
import me.idriz.oss.commands.Command;
import me.idriz.oss.commands.annotation.CommandInfo;
import me.idriz.oss.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandInfo("stopgame")
public class StopGameCommand implements Command {

    @Default
    public void stopGame(CommandSender sender) {
        Bukkit.getScheduler().cancelTasks(Skyfall.getInstance());
        sender.sendMessage(ChatColor.GREEN + "The game was stopped.");
    }
}
