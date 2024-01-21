package lt.bongibau.welcomemessagereloaded.message.type;

import lt.bongibau.welcomemessagereloaded.WelcomeMessageReloaded;
import lt.bongibau.welcomemessagereloaded.message.IMessage;
import lt.bongibau.welcomemessagereloaded.message.replacer.NormalReplacer;
import lt.bongibau.welcomemessagereloaded.message.replacer.PlaceholderAPIReplacer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collections;
import java.util.List;

public class PrivateMessage implements IMessage {

    private final List<String> messages;

    private final int delay;

    private final boolean isFirstTimeOnly;

    private final boolean isExceptFirstTime;

    public PrivateMessage(List<String> messages, int delay, boolean isFirstTimeOnly, boolean isExceptFirstTime) {
        this.messages = messages;
        this.delay = delay;
        this.isFirstTimeOnly = isFirstTimeOnly;
        this.isExceptFirstTime = isExceptFirstTime;
    }

    @Override
    public void immediateSend(Player player) {
        for (String message : this.messages) {
            if (WelcomeMessageReloaded.getInstance().isPlaceholderAPIEnabled()) {
                player.sendMessage(PlaceholderAPIReplacer.replace(message, player));
            } else {
                player.sendMessage(NormalReplacer.replace(message, player));
            }
        }
    }

    @Override
    public int getDelay() {
        return this.delay;
    }

    @Override
    public JavaPlugin getPlugin() {
        return WelcomeMessageReloaded.getInstance();
    }

    public @Unmodifiable List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    @Override
    public boolean isExceptFirstTime() {
        return isExceptFirstTime;
    }

    @Override
    public boolean isFirstTimeOnly() {
        return isFirstTimeOnly;
    }
}
