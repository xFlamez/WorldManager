package me.redblackflamez.worldmanager.worldmanager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class DeleteWorld {
    public static boolean deleteWorld(CommandSender sender, String worldName) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) sender;
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            player.sendMessage("World '" + worldName + "' does not exist.");
            return false;
        }

        boolean deleted = Bukkit.unloadWorld(world, false);
        if (!deleted) {
            player.sendMessage("Failed to unload world '" + worldName + "'.");
            return false;
        }

        boolean worldDeleted = deleteWorldFiles(world);
        if (!worldDeleted) {
            player.sendMessage("Failed to delete world files for '" + worldName + "'.");
            return false;
        }

        player.sendMessage("World '" + worldName + "' has been deleted.");
        return true;
    }

    private static boolean deleteWorldFiles(World world) {
        try {
            File worldFolder = world.getWorldFolder();
            if (worldFolder.exists()) {
                FileUtils.deleteDirectory(worldFolder);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
