package pl.memexurer.antymakiero.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class ChatUtil {
    public static String fixColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void broadcastPermission(String message, String permission) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.hasPermission(permission)) continue;
            p.sendMessage(fixColor(message));
        }
    }
}
