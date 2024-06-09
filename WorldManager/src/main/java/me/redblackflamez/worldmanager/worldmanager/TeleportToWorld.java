package me.redblackflamez.worldmanager.worldmanager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static me.redblackflamez.worldmanager.Utils.processColorCodes;

public class TeleportToWorld {
    public static void teleportToWorld(Player player, World world) {
        if (world != null) {
            player.teleport(world.getSpawnLocation());
            player.sendMessage(processColorCodes("&7Sucessfully teleport to world `&e%world%&7`!").replace("%world%", world.getName()));
            return;
        }
        player.sendMessage(processColorCodes("&7No world `&e%world%&7` found!").replace("%world%", world.getName()));
    }
}
