package lt.bongibau.welcomemessagereloaded.message.replacer;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlaceholderAPIReplacer {
    public static String replace(String message, Player player) {
        if (message == null || message.isEmpty())
            return null;

        return PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', message));
    }
}
