package lt.bongibau.welcomemessagereloaded.message.configuration;

import lt.bongibau.welcomemessagereloaded.WelcomeMessageReloaded;
import lt.bongibau.welcomemessagereloaded.message.type.BroadcastMessage;
import lt.bongibau.welcomemessagereloaded.message.IMessage;
import lt.bongibau.welcomemessagereloaded.message.type.PrivateMessage;
import lt.bongibau.welcomemessagereloaded.message.type.SoundMessage;
import lt.bongibau.welcomemessagereloaded.message.type.TitleMessage;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MessageConfiguration {

    private final LYamlConfigurationFile configuration;

    public MessageConfiguration() {
        this.configuration = new LYamlConfigurationFile("config", WelcomeMessageReloaded.getInstance());
    }

    public List<IMessage> getSoundMessages() {
        ArrayList<IMessage> messages = new ArrayList<>();

        YamlConfiguration config = this.configuration.getConfiguration();

        if (!config.getBoolean("welcome-sound.enabled")) return messages;

        ConfigurationSection section = config.getConfigurationSection("welcome-sound");

        if (section == null) return messages;

        for (String key : section.getKeys(false)) {
            String path = "welcome-sound." + key + ".";

            if (key.equals("enabled")) continue;

            String soundName = config.getString(path + "sound");
            if (soundName == null) continue;

            Sound sound = Sound.valueOf(soundName);
            float volume = (float) config.getDouble(path + "volume");
            float pitch = (float) config.getDouble(path + "pitch");
            int delay = config.getInt(path + "delay");
            boolean isFirstTimeOnly = config.getBoolean(path + "first-time-only");
            boolean isExceptFirstTime = config.getBoolean(path + "except-first-time");

            messages.add(new SoundMessage(sound, volume, pitch, delay, isFirstTimeOnly, isExceptFirstTime));
        }

        return messages;
    }

    public List<IMessage> getBroadcastMessages() {
        ArrayList<IMessage> messages = new ArrayList<>();

        YamlConfiguration config = this.configuration.getConfiguration();

        if (!config.getBoolean("welcome-broadcast.enabled")) return messages;

        ConfigurationSection section = config.getConfigurationSection("welcome-broadcast");

        if (section == null) return messages;

        for (String key : section.getKeys(false)) {
            String path = "welcome-broadcast." + key + ".";

            if (key.equals("enabled")) continue;

            List<String> messagesList = config.getStringList(path + "messages");
            if (messagesList.isEmpty()) continue;

            int delay = config.getInt(path + "delay");
            boolean isFirstTimeOnly = config.getBoolean(path + "first-time-only");
            boolean isExceptFirstTime = config.getBoolean(path + "except-first-time");

            messages.add(new BroadcastMessage(messagesList, delay, isFirstTimeOnly, isExceptFirstTime));
        }

        return messages;
    }

    public List<IMessage> getPrivateMessages() {
        ArrayList<IMessage> messages = new ArrayList<>();

        YamlConfiguration config = this.configuration.getConfiguration();

        if (!config.getBoolean("welcome-message.enabled")) return messages;

        ConfigurationSection section = config.getConfigurationSection("welcome-message");

        if (section == null) return messages;

        for (String key : section.getKeys(false)) {
            String path = "welcome-message." + key + ".";

            if (key.equals("enabled")) continue;

            List<String> messagesList = config.getStringList(path + "messages");
            if (messagesList.isEmpty()) continue;

            int delay = config.getInt(path + "delay");
            boolean isFirstTimeOnly = config.getBoolean(path + "first-time-only");
            boolean isExceptFirstTime = config.getBoolean(path + "except-first-time");

            messages.add(new PrivateMessage(messagesList, delay, isFirstTimeOnly, isExceptFirstTime));
        }

        return messages;
    }

    public List<IMessage> getTitleMessages() {
        ArrayList<IMessage> messages = new ArrayList<>();

        YamlConfiguration config = this.configuration.getConfiguration();

        if (!config.getBoolean("welcome-title.enabled")) return messages;

        ConfigurationSection section = config.getConfigurationSection("welcome-title");

        if (section == null) return messages;

        for (String key : section.getKeys(false)) {
            String path = "welcome-title." + key + ".";

            if (key.equals("enabled")) continue;

            String title = config.getString(path + "title");
            if (title == null) continue;

            String subtitle = config.getString(path + "subtitle");
            int fadeIn = config.getInt(path + "fade-in");
            int stay = config.getInt(path + "stay");
            int fadeOut = config.getInt(path + "fade-out");
            int delay = config.getInt(path + "delay");
            boolean isFirstTimeOnly = config.getBoolean(path + "first-time-only");
            boolean isExceptFirstTime = config.getBoolean(path + "except-first-time");

            messages.add(new TitleMessage(title, subtitle, fadeIn, stay, fadeOut, delay, isFirstTimeOnly, isExceptFirstTime));
        }

        return messages;
    }

    public List<IMessage> getMessages() {
        ArrayList<IMessage> messages = new ArrayList<>();

        messages.addAll(this.getSoundMessages());
        messages.addAll(this.getBroadcastMessages());
        messages.addAll(this.getPrivateMessages());
        messages.addAll(this.getTitleMessages());

        return messages;
    }

    public String getString(String path) {
        return this.configuration.getMessage(path);
    }

    public boolean getBoolean(String path) {
        return this.configuration.getConfiguration().getBoolean(path);
    }
}
