package lt.bongibau.welcomemessagereloaded.message.type;

import lt.bongibau.welcomemessagereloaded.WelcomeMessageReloaded;
import lt.bongibau.welcomemessagereloaded.message.IMessage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SoundMessage implements IMessage {

    private final Sound sound;

    private final float volume;

    private final float pitch;

    private final int delay;

    private final boolean isFirstTimeOnly;

    private final boolean isExceptFirstTime;

    public SoundMessage(Sound sound, float volume, float pitch, int delay, boolean isFirstTimeOnly, boolean isExceptFirstTime) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.delay = delay;
        this.isFirstTimeOnly = isFirstTimeOnly;
        this.isExceptFirstTime = isExceptFirstTime;
    }

    @Override
    public void immediateSend(Player player) {
        player.playSound(player.getLocation(), this.sound, this.volume, this.pitch);
    }

    @Override
    public int getDelay() {
        return this.delay;
    }

    @Override
    public JavaPlugin getPlugin() {
        return WelcomeMessageReloaded.getInstance();
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
