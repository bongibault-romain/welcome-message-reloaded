package lt.bongibau.welcomemessagereloaded.message;

import lt.bongibau.welcomemessagereloaded.WelcomeMessageReloaded;
import lt.bongibau.welcomemessagereloaded.message.configuration.MessageConfiguration;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collections;
import java.util.List;

public class MessageManager {

    private static final MessageManager instance = new MessageManager();

    private MessageListener listener;

    private MessageConfiguration configuration;

    private List<IMessage> messages;

    public void load() {
        this.configuration = new MessageConfiguration();
        this.listener = new MessageListener();

        this.messages = this.configuration.getMessages();

        System.out.println("Loaded " + this.messages.size() + " messages");
        messages.forEach(message -> System.out.println("Loaded message: " + message.getClass().getSimpleName()));

        WelcomeMessageReloaded.getInstance().getServer().getPluginManager().registerEvents(this.listener, WelcomeMessageReloaded.getInstance());
    }

    public void unload() {
        HandlerList.unregisterAll(this.listener);

        this.messages.clear();
        this.messages = null;

        this.configuration = null;
        this.listener = null;
    }

    public static MessageManager getInstance() {
        return instance;
    }

    public @Unmodifiable List<IMessage> getMessages() {
        return Collections.unmodifiableList(this.messages);
    }

    public MessageConfiguration getConfiguration() {
        return this.configuration;
    }
}
