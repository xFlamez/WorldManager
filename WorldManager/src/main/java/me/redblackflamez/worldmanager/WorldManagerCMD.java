package me.redblackflamez.worldmanager;

import me.redblackflamez.worldmanager.worldmanager.CreateWorld;
import me.redblackflamez.worldmanager.worldmanager.DeleteWorld;
import me.redblackflamez.worldmanager.worldmanager.TeleportToWorld;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.redblackflamez.worldmanager.Utils.processColorCodes;
import static me.redblackflamez.worldmanager.WorldManager.lang;
import static me.redblackflamez.worldmanager.WorldManager.plugin;
import static me.redblackflamez.worldmanager.worldmanager.Settings.*;

public class WorldManagerCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("world") && args.length > 1) {
                for (World world : Bukkit.getWorlds()) {
                    if (args[1].equalsIgnoreCase(world.getName())) {
                        String worldName = world.getName();
                        if (args.length > 2) {
                            if (args[2].equalsIgnoreCase("pvp")) {
                                handlePvpToggle(player, world, args);
                                return true;
                            } else if (args[2].equalsIgnoreCase("difficulty")) {
                                handleDifficultyToggle(player, world, args);
                                return true;
                            } else if (args[2].equalsIgnoreCase("hardcore")) {
                                handleHardcoreToggle(player, world, args);
                                return true;
                            } else if (args[2].equalsIgnoreCase("keep-inv")) {
                                handleKeepInventoryToggle(player, world, args);
                                return true;
                            } else if (args[2].equalsIgnoreCase("setspawn") || args[2].equalsIgnoreCase("set-spawn")) {
                                handleSetSpawnChange(player, world, args);
                                return true;
                            } else {
                                player.sendMessage("Unknown setting '" + args[2] + "'.");
                                return true;
                            }
                        } else {
                            player.sendMessage("&cMissing arguments for setting.");
                            return true;
                        }
                    }
                }
                player.sendMessage("Unknown world '" + args[1] + "'!");
                return true;
            } else if (args[0].equalsIgnoreCase("create") && args.length > 1) {
                if (args.length == 2) {
                    CreateWorld.createWorld(args[1], "DEFAULT", player);
                    return true;
                } else {
                    CreateWorld.createWorld(args[1], args[2], player);
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("delete") && args.length > 1) {
                String worldName = args[1];
                boolean success = DeleteWorld.deleteWorld(player, worldName);
                if (success) {
                    player.sendMessage(processColorCodes("&7Successfully deleted world '&e%world%&7'!").replace("%world%", worldName));
                }
                return true;
            } else if ((args[0].equalsIgnoreCase("tp") || args[0].equalsIgnoreCase("teleport")) && args.length > 1) {
                for (World world : Bukkit.getWorlds()) {
                    if (args[1].equalsIgnoreCase(world.getName())) {
                        TeleportToWorld.teleportToWorld(player, world);
                        return true;
                    }
                }
                player.sendMessage(processColorCodes("&7Failed to teleport you to world '%world%'").replace("%world%", args[1]));
                return true;
            } else {
                player.sendMessage("Usage: /world <world_name> <setting> [value]");
                return true;
            }
        } else {
            player.sendMessage("Usage: /world <world_name> <setting> [value]");
            return true;
        }
    }
}
