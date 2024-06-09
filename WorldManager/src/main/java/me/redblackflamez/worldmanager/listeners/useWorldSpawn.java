package me.redblackflamez.worldmanager.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import static me.redblackflamez.worldmanager.WorldManager.plugin;

public class useWorldSpawn implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location loc = plugin.getConfig().getLocation("worlds." + player.getWorld().getName() + ".spawn");
        if (loc != null) {
            player.teleport(loc);
        }
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location loc = plugin.getConfig().getLocation("worlds." + player.getWorld().getName() + ".spawn");
        if (loc != null) {
            player.teleport(loc);
        }
    }
}
