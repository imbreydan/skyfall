package dev.breydan.skyfall.command;

import dev.breydan.skyfall.Skyfall;
import dev.breydan.skyfall.task.TeleportTask;
import me.idriz.oss.commands.Command;
import me.idriz.oss.commands.annotation.CommandInfo;
import me.idriz.oss.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@CommandInfo(value = "startgame", aliases = "start")
public class StartGameCommand implements Command {

    private static final Map<Integer, ChatColor> CHAT_COLORS = new HashMap<>();

    static {
        ChatColor[] colors = {
                ChatColor.GREEN, ChatColor.YELLOW,
                ChatColor.GOLD, ChatColor.RED, ChatColor.DARK_RED
        };

        IntStream.rangeClosed(1, colors.length).forEach(i -> CHAT_COLORS.put(i, colors[i - 1]));
    }

    private int count = 5;

    @Default
    public void startGame(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can run this command.");
            return;
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(Skyfall.getInstance(), task -> {
            if (count == 0) {
                Bukkit.getScheduler().runTaskTimer(Skyfall.getInstance(), new TeleportTask((Player) sender), 0, 20);
                sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "SkyFall Started. Go Go Go!");
                task.cancel();
            } else {
                sender.sendMessage(ChatColor.GRAY + "Game starting in " + CHAT_COLORS.get(count) + count);
                count--;
            }
        }, 0, 20);
    }
}
