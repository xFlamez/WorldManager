package me.redblackflamez.worldmanager;

import me.redblackflamez.worldmanager.listeners.useWorldSpawn;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldManager extends JavaPlugin {
    public static WorldManager plugin;
    public static YamlConfiguration lang;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new useWorldSpawn(), this);

        getCommand("worldmanager").setExecutor(new WorldManagerCMD());
        // getCommand("worldmanager").setTabCompleter(new WorldManagerTAB())

        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
