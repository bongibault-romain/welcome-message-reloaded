
# Welcome Message Reloaded

Welcome Message Reloaded is a plugin allowing to welcome the player connecting for the first time to the server in the most personalized way possible. For this you can use messages, sounds or titles.

## Installation

Put the plugin in your `plugins` folder and restart your server.

## Configuration

The configuration is distinguished in five parts. The first allows to configure the messages used in the `/wmr` command, allowing to reload the configuration.


```yaml
# Messages for plugin commands.
messages:
  no-permission: '&cYou do not have permission to use this command.'
  config-reloaded: '&aConfiguration reloaded.'
  command-not-found: '&cCommand not found.'
```

Then, the second allows you to configure whether a sound will be played when a player connects for the first time. The **list of sounds** available differs depending on the **version of Minecraft on your server**

```yaml
# Play a sound when a player joins the server for the first time.
welcome-sound:
  enabled: true
  first-time-only: true
  sound: ENTITY_PLAYER_LEVELUP
  volume: 1
  pitch: 1
```

In all the next categories, there is a list of keywords that can be used, such as `%player%`, and which will be replaced by their value. **You can find the list of these keywords at the bottom of the page**.

This part of the configuration allows you to display a title (and subtitle) to the player.

<img width="1079" alt="1" src="https://github.com/bongibault-romain/welcome-message-reloaded/assets/77286154/8c2ee251-aff8-40a2-aac5-842252e03c27">

```yaml
# Send a title message when a player joins the server for the first time.
welcome-title:
  enabled: true
  first-time-only: false
  title: '&6&lWelcome %player%!'
  subtitle-enabled: true
  subtitle: '&e&lEnjoy your stay!'
  fade-in: 20
  stay: 40
  fade-out: 20
```

This part of the configuration allows you to send a message to all players.

```yaml
# Send a broadcast message when a player joins the server for the first time.
welcome-broadcast:
  enabled: true
  first-time-only: true
  messages:
    - '&6&lWelcome %player% to the server!'
```

This part of the configuration allows you to send a message only to the player who just logged in.

```yaml
# Send a private chat message when a player joins the server for the first time.
welcome-message:
  enabled: true
  first-time-only: true
  messages:
    - 'Welcome %player% to the server!'

```

## Usage

Wait for a player to connect to the server!

## Keywords

| Keyword      | Value                    |
|--------------|--------------------------|
| %player%     | Player Name              |
| %online%     | Players Online Count     |
| %maxplayers% | Maximum Number Of Player |
| %version%    | Server Minecraft Version |

### How to add a keyword ?

If you are a developer and want to add your keywords, you just need to add the dependency plugin and call this function:

```java
WelcomeMessageReloaded.getInstance().register("keyword", "value");
```
