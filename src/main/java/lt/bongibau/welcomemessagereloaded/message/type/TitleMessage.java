package lt.bongibau.welcomemessagereloaded.message.type;

import lt.bongibau.welcomemessagereloaded.WelcomeMessageReloaded;
import lt.bongibau.welcomemessagereloaded.message.IMessage;
import lt.bongibau.welcomemessagereloaded.message.replacer.NormalReplacer;
import lt.bongibau.welcomemessagereloaded.message.replacer.PlaceholderAPIReplacer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public class TitleMessage implements IMessage {

    private final String title;

    @Nullable
    private final String subtitle;

    private final int fadeIn;

    private final int stay;

    private final int fadeOut;

    private final int delay;

    private final boolean isFirstTimeOnly;

    private final boolean isExceptFirstTime;

    public TitleMessage(String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut, int delay, boolean isFirstTimeOnly, boolean isExceptFirstTime) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.delay = delay;
        this.isFirstTimeOnly = isFirstTimeOnly;
        this.isExceptFirstTime = isExceptFirstTime;
    }

    @Override
    public void immediateSend(Player player) {
        if (WelcomeMessageReloaded.getInstance().isPlaceholderAPIEnabled()) {
            player.sendTitle(PlaceholderAPIReplacer.replace(this.title, player), PlaceholderAPIReplacer.replace(this.subtitle, player), this.fadeIn, this.stay, this.fadeOut);
        } else {
            player.sendTitle(NormalReplacer.replace(this.title, player), NormalReplacer.replace(this.subtitle, player), this.fadeIn, this.stay, this.fadeOut);
        }
    }

    public String getTitle() {
        return title;
    }

    public @Nullable String getSubtitle() {
        return subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeOut() {
        return fadeOut;
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
