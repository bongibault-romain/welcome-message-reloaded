package lt.bongibau.welcomemessagereloaded.message.replacer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NormalReplacer {
    public static String replace(String message, Player player) {
        if (message == null || message.isEmpty())
            return null;

        return ChatColor.translateAlternateColorCodes('&', message)
                .replaceAll("%player_name%", player.getName());
    }
}
