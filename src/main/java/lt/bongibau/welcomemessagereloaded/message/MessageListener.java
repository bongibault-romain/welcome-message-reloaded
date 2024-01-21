package lt.bongibau.welcomemessagereloaded.message;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class MessageListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        List<IMessage> messages = MessageManager.getInstance().getMessages();
        boolean hasPlayedBefore = player.hasPlayedBefore();

        if (MessageManager.getInstance().getConfiguration().getBoolean("disable-default-join-message")) {
            e.setJoinMessage("");
        }

        for (IMessage message : messages) {
            if (message.isFirstTimeOnly() && hasPlayedBefore) {
                continue;
            }

            if (message.isExceptFirstTime() && !hasPlayedBefore) {
                continue;
            }

            message.send(player);
        }
    }

}
