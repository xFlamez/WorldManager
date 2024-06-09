package me.redblackflamez.worldmanager.worldmanager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import static me.redblackflamez.worldmanager.Utils.processColorCodes;

public class CreateWorld {
    public static void createWorld(String worldName, String type, Player player) {
        World w = Bukkit.getWorld(worldName);
        for (World world : Bukkit.getWorlds()) {
            if (world == w) {
                player.sendMessage(processColorCodes("&7World with name '&e%world%&7' already exists."));
                return;
            }
        }

        String generatorType = type.toUpperCase();

        WorldCreator worldCreator = new WorldCreator(worldName);
        switch (generatorType) {
            case "DEFAULT":
                worldCreator.type(WorldType.NORMAL);
                break;
            case "FLAT":
                worldCreator.type(WorldType.FLAT);
                break;
            case "AMPLIFIED":
                worldCreator.type(WorldType.AMPLIFIED);
                break;
            case "LARGE_BIOMES":
                worldCreator.type(WorldType.LARGE_BIOMES);
                break;
            default:
                player.sendMessage("Unknown generator type '" + generatorType + "'. Use one of: DEFAULT, FLAT, AMPLIFIED, LARGE_BIOMES.");
                return;
        }

        Bukkit.createWorld(worldCreator);
        player.sendMessage(processColorCodes("&7Successfully created world '&d%world%&7' with generator '&e%generator%&7!'").replace("%world%", worldName).replace("%generator%", generatorType));
    }
}
