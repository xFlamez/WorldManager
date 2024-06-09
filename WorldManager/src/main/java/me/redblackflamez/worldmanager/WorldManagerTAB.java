package me.redblackflamez.worldmanager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WorldManagerTAB implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> args1 = new ArrayList<>();
        if (args[0].equalsIgnoreCase("world")) {
            if (args[1].isBlank()) {
                for (World world : Bukkit.getWorlds()) {
                    args1.add(world.getName());
                }
            }
        } else if (args[0].isBlank()) {
            args1.add("reload");
            args1.add("world");
        }

        return args1;
    }
}
