package me.redblackflamez.worldmanager;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.redblackflamez.worldmanager.WorldManager.plugin;

public class Utils {
    public static String processColorCodes(String input) {
        if (input == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("(?i)(&#[0-9A-Fa-f]{6})").matcher(input);
        while (matcher.find()) {
            String hexColor = matcher.group().replace("&#", "#");
            ChatColor color = ChatColor.of(hexColor);
            input = input.replaceFirst("(?i)(&#[0-9A-Fa-f]{6})", color.toString());
        }
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public static void saveConfig() {
        try {
            plugin.reloadConfig();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
