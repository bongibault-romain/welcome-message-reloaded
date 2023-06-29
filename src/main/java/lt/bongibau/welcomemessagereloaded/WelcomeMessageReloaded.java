package lt.bongibau.welcomemessagereloaded;

import lt.bongibau.welcomemessagereloaded.configuration.LYamlConfigurationFile;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class WelcomeMessageReloaded extends JavaPlugin implements Listener, CommandExecutor {

    private WelcomeMessageReloaded instance = null;

    private final LYamlConfigurationFile config = new LYamlConfigurationFile("config", this);

    private final HashMap<String, String> replacements = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        this.config.load();

        this.getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("wmr")).setExecutor(this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void register(String key, String value) {
        this.replacements.put(key, value);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            sender.sendMessage(this.config.getMessage("messages.command-not-found"));
            return true;
        }

        if (sender.hasPermission("wmr.reload")) {
            this.config.load();
            sender.sendMessage(this.config.getMessage("messages.config-reloaded"));
        } else {
            sender.sendMessage(this.config.getMessage("messages.no-permission"));
        }

        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        boolean welcomeMessageFirstTimeOnly = this.config.getConfiguration().getBoolean("welcome-message.first-time-only");
        boolean welcomeBroadcastFirstTimeOnly = this.config.getConfiguration().getBoolean("welcome-broadcast.first-time-only");
        boolean welcomeTitleFirstTimeOnly = this.config.getConfiguration().getBoolean("welcome-title.first-time-only");
        boolean welcomeSoundFirstTimeOnly = this.config.getConfiguration().getBoolean("welcome-sound.first-time-only");

        boolean hasPlayedBefore = player.hasPlayedBefore();

        if (welcomeMessageFirstTimeOnly && welcomeBroadcastFirstTimeOnly && welcomeTitleFirstTimeOnly && welcomeSoundFirstTimeOnly && hasPlayedBefore) return;

        /*
         * The runnable ensures that the join message
         * is sent before the welcome message.
         */
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, (Runnable) () -> {
            if (!player.isOnline()) return;

            this.register("player", player.getName());
            this.register("online", this.formatNumber(this.getServer().getOnlinePlayers().size()));
            this.register("maxplayers", this.formatNumber(this.getServer().getMaxPlayers()));
            this.register("version", this.getServer().getVersion());

            if (this.config.getConfiguration().getBoolean("welcome-message.enabled") && (!welcomeMessageFirstTimeOnly || !hasPlayedBefore)) {
                List<String> messages = this.config.getMessageList("welcome-message.messages", this.replacements);

                for (String message : messages) {
                    player.sendMessage(message);
                }
            }

            if (this.config.getConfiguration().getBoolean("welcome-broadcast.enabled") && (!welcomeBroadcastFirstTimeOnly || !hasPlayedBefore)) {
                List<String> messages = this.config.getMessageList("welcome-broadcast.messages", this.replacements);

                for (String message : messages) {
                    this.getServer().broadcastMessage(message);
                }
            }

            if (this.config.getConfiguration().getBoolean("welcome-title.enabled") && (!welcomeTitleFirstTimeOnly || !hasPlayedBefore)) {
                String title = this.config.getMessage("welcome-title.title", this.replacements);
                String subtitle = this.config.getMessage("welcome-title.subtitle", this.replacements);
                boolean subtitleEnabled = this.config.getConfiguration().getBoolean("welcome-title.subtitle-enabled");
                int fadeIn = this.config.getConfiguration().getInt("welcome-title.fade-in");
                int stay = this.config.getConfiguration().getInt("welcome-title.stay");
                int fadeOut = this.config.getConfiguration().getInt("welcome-title.fade-out");

                player.sendTitle(title, subtitleEnabled ? subtitle : null, fadeIn, stay, fadeOut);
            }

            if (this.config.getConfiguration().getBoolean("welcome-sound.enabled") && (!welcomeSoundFirstTimeOnly || !hasPlayedBefore)) {
                String soundString = this.config.getConfiguration().getString("welcome-sound.sound");
                Sound sound = Sound.valueOf(soundString);

                double volume = this.config.getConfiguration().getDouble("welcome-sound.volume");
                double pitch = this.config.getConfiguration().getDouble("welcome-sound.pitch");

                player.playSound(player.getLocation(), sound, (float) volume, (float) pitch);
            }
        }, 2L);
    }

    private String formatNumber(int number) {
        return new DecimalFormat("#,###,###,###").format(number);
    }

    public WelcomeMessageReloaded getInstance() {
        return instance;
    }
}
