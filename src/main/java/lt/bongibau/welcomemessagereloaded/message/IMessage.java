package lt.bongibau.welcomemessagereloaded.message;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface IMessage {

    default void send(Player player) {
        if (getDelay() == 0) {
            this.immediateSend(player);
            return;
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(getPlugin(), () -> {
            this.immediateSend(player);
        }, getDelay());
    }

    void immediateSend(Player player);

    int getDelay();

    JavaPlugin getPlugin();

    boolean isFirstTimeOnly();

    boolean isExceptFirstTime();
}
