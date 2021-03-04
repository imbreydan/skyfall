package dev.breydan.skyfall.task;

import dev.breydan.skyfall.Skyfall;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TeleportTask implements Runnable {

    private final Player player;
    private int count = Skyfall.getInstance().getConfig().getInt("teleport-interval-seconds");
    private int height = Skyfall.getInstance().getConfig().getInt("teleport-height");

    public TeleportTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        switch (count) {
            case 5:
                player.sendMessage(ChatColor.GREEN + "Teleporting in " + count + "...");
                break;
            case 4:
            case 3:
            case 2:
            case 1:
                player.sendMessage(ChatColor.GREEN.toString() + count + "...");
                break;
            case 0:
                if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                    player.sendMessage(ChatColor.GREEN + "You would have been teleported, but you're in the nether.");
                    return;
                }
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                // Loop through x blocks above the player
                for (int i = 1; i <= height; i++) {
                    if (player.getWorld().getBlockAt(new Location(player.getWorld(), x, y + i, z)).getType() != Material.AIR) {
                        player.teleportAsync(new Location(player.getWorld(), x, y + i - 2, z));
                        player.sendMessage(ChatColor.GREEN + "There was a block over you, so you were teleported there.");
                        count = Skyfall.getInstance().getConfig().getInt("teleport-interval-seconds");;
                        return;
                    }
                }
                player.teleportAsync(new Location(player.getWorld(), x, y + height, z));
                player.sendMessage(ChatColor.GREEN + "You were teleported 100 blocks into the sky!");
                count = Skyfall.getInstance().getConfig().getInt("teleport-interval-seconds");;
        }
        count--;
    }
}
