package lt.bongibau.welcomemessagereloaded;

import lt.bongibau.welcomemessagereloaded.message.configuration.LYamlConfigurationFile;
import lt.bongibau.welcomemessagereloaded.message.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class WelcomeMessageReloaded extends JavaPlugin implements CommandExecutor {

    private static WelcomeMessageReloaded instance = null;

    private boolean isPlaceholderAPIEnabled = false;

    @Override
    public void onEnable() {
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            this.isPlaceholderAPIEnabled = true;
        }

        MessageManager.getInstance().load();

        Objects.requireNonNull(this.getCommand("wmr")).setExecutor(this);

        Bukkit.getPluginManager().addPermission(new Permission("wmr.reload", PermissionDefault.OP));
    }

    @Override
    public void onDisable() {
        MessageManager.getInstance().unload();

        instance = null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 0) {
            sender.sendMessage(MessageManager.getInstance().getConfiguration().getString("messages.command-not-found"));
            return true;
        }

        if (sender.hasPermission("wmr.reload")) {
            MessageManager.getInstance().unload();
            MessageManager.getInstance().load();
            sender.sendMessage(MessageManager.getInstance().getConfiguration().getString("messages.config-reloaded"));
        } else {
            sender.sendMessage(MessageManager.getInstance().getConfiguration().getString("messages.no-permission"));
        }

        return true;
    }

    public boolean isPlaceholderAPIEnabled() {
        return isPlaceholderAPIEnabled;
    }

    public static WelcomeMessageReloaded getInstance() {
        return instance;
    }
}
