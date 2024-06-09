package me.redblackflamez.worldmanager.worldmanager;

import org.bukkit.*;
import org.bukkit.entity.Player;

import static me.redblackflamez.worldmanager.Utils.processColorCodes;
import static me.redblackflamez.worldmanager.WorldManager.plugin;

public class Settings {
    public static void handlePvpToggle(Player player, World world, String[] args) {
        String worldName = world.getName();
        if (args.length == 3) {
            world.setPVP(!world.getPVP());
            player.sendMessage(processColorCodes("&7Successfully toggled &bPVP&7 in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", String.valueOf(world.getPVP())));
        } else if (args.length == 4 && (args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false"))) {
            world.setPVP(Boolean.parseBoolean(args[3]));
            player.sendMessage(processColorCodes("&7Successfully set &bPVP&7 in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", String.valueOf(world.getPVP())));
        }
    }

    public static void handleDifficultyToggle(Player player, World world, String[] args) {
        String worldName = world.getName();
        if (args.length == 3) {
            Difficulty newDifficulty;
            switch (world.getDifficulty()) {
                case HARD -> newDifficulty = Difficulty.PEACEFUL;
                case NORMAL -> newDifficulty = Difficulty.HARD;
                case EASY -> newDifficulty = Difficulty.NORMAL;
                case PEACEFUL -> newDifficulty = Difficulty.EASY;
                default -> throw new IllegalStateException("Unexpected value: " + world.getDifficulty());
            }
            world.setDifficulty(newDifficulty);
            player.sendMessage(processColorCodes("&7Successfully toggled &bDifficulty&7 in world '&d%world%&7' to &e%difficulty%&7!")
                    .replace("%world%", worldName).replace("%difficulty%", world.getDifficulty().toString()));
        } else if (args.length == 4 && (args[3].equalsIgnoreCase("PEACEFUL") || args[3].equalsIgnoreCase("EASY") ||
                args[3].equalsIgnoreCase("NORMAL") || args[3].equalsIgnoreCase("HARD"))) {
            world.setDifficulty(Difficulty.valueOf(args[3].toUpperCase()));
            player.sendMessage(processColorCodes("&7Successfully set &bDifficulty&7 in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", world.getDifficulty().toString()));
        }
    }

    public static void handleHardcoreToggle(Player player, World world, String[] args) {
        String worldName = world.getName();
        if (args.length == 3) {
            world.setHardcore(!world.isHardcore());
            player.sendMessage(processColorCodes("&7Successfully toggled &bHardcore&7 mode in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", String.valueOf(world.isHardcore())));
        } else if (args.length == 4 && (args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false"))) {
            world.setHardcore(Boolean.parseBoolean(args[3]));
            player.sendMessage(processColorCodes("&7Successfully set &bHardcore&7 mode in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", String.valueOf(world.isHardcore())));
        }
    }
    public static void handleKeepInventoryToggle(Player player, World world, String[] args) {
        String worldName = world.getName();
        if (args.length == 3) {
            boolean currentValue = world.getGameRuleValue(GameRule.KEEP_INVENTORY);
            world.setGameRule(GameRule.KEEP_INVENTORY, !currentValue);
            player.sendMessage(processColorCodes("&7Successfully toggled &bkeep inventory&7 in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", String.valueOf(!currentValue)));
        } else if (args.length == 4 && (args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false"))) {
            boolean newValue = Boolean.parseBoolean(args[3]);
            world.setGameRule(GameRule.KEEP_INVENTORY, newValue);
            player.sendMessage(processColorCodes("&7Successfully set &bkeep inventory&7 in world '&d%world%&7' to &e%value%&7!")
                    .replace("%world%", worldName).replace("%value%", String.valueOf(newValue)));
        } else {
            player.sendMessage("Invalid arguments for keep inventory setting.");
        }
    }
    public static void handleSetSpawnChange(Player player, World world, String[] args) {
        String worldName = world.getName();
        if (args.length < 6) {
            player.sendMessage(processColorCodes("&7Usage: /worldmanager world %world% setspawn <x> <y> <z> [yaw] [pitch]").replace("%world%", worldName));
            return;
        }

        try {
            double x = Double.parseDouble(args[3]);
            double y = Double.parseDouble(args[4]);
            double z = Double.parseDouble(args[5]);
            float yaw = args.length > 6 ? Float.parseFloat(args[6]) : 0.0f;
            float pitch = args.length > 7 ? Float.parseFloat(args[7]) : 0.0f;

            Location loc = new Location(world, x, y, z, yaw, pitch);
            world.setSpawnLocation(loc);

            player.sendMessage(processColorCodes("&7Successfully set the spawn location in world '&d%world%&7' to &e%x%, %y%, %z%&7 with yaw &e%yaw%&7 and pitch &e%pitch%&7!")
                    .replace("%world%", worldName)
                    .replace("%x%", String.valueOf(x))
                    .replace("%y%", String.valueOf(y))
                    .replace("%z%", String.valueOf(z))
                    .replace("%yaw%", String.valueOf(yaw))
                    .replace("%pitch%", String.valueOf(pitch)));
        } catch (NumberFormatException e) {
            player.sendMessage(processColorCodes("&cInvalid coordinates. Please enter valid numbers for <x>, <y>, <z>, [yaw], and [pitch]."));
        }
    }
}
