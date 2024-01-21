
# Welcome Message Reloaded

Welcome Message Reloaded is a plugin allowing to welcome the player connecting for the first time to the server in the most personalized way possible. For this you can use messages, sounds or titles.

## Installation

Put the plugin in your `plugins` folder and restart your server.

## Configuration

The configuration is distinguished in five parts. The first allows to configure the messages used in the `/wmr` command, allowing to reload the configuration. The permission to use this command is `wmr.reload`.

```yaml
# Messages for plugin commands.
messages:
  no-permission: '&cYou do not have permission to use this command.'
  config-reloaded: '&aConfiguration reloaded.'
  command-not-found: '&cCommand not found.'
```

Then, the second allows you to configure whether a sound will be played when a player connects for the first time. The **list of sounds** available differs depending on the **version of Minecraft on your server**. You can add as much sound as you want, just copy the example, **change the name of the example** and change the name of the sound.

You can also add a delay **in tick** before the sound is played.
```yaml
# Play a sound when a player joins the server for the first time.
# See https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html for a list of sounds.
welcome-sound:
  enabled: true
  example-sound:
    first-time-only: true
    except-first-time: false
    sound: ENTITY_PLAYER_LEVELUP
    volume: 1.0
    pitch: 1.0
    delay: 0
  # You can add more sounds here.
```

In all the next categories, there is a list of keywords that can be used, such as `%player_name%`, and which will be replaced by their value. **You can find the list of these keywords at the bottom of the page**.

As for the sounds, you can add as much message as you want in each category, just copy the example, **change the name of the example** and change the parameters.

This part of the configuration allows you to display a title (and subtitle) to the player.

<img width="1079" alt="1" src="https://github.com/bongibault-romain/welcome-message-reloaded/assets/77286154/8c2ee251-aff8-40a2-aac5-842252e03c27">

```yaml
# Send a title message when a player joins the server for the first time.
welcome-title:
  enabled: true
  example-title:
    first-time-only: true
    except-first-time: false
    title: '&6&lWelcome %player_name%!'
    subtitle: '&e&lEnjoy your stay!'
    fade-in: 20
    stay: 40
    fade-out: 20
    delay: 0
  # You can add more title messages here.
```

If you want to remove the subtitle in game, just remove the `subtitle` line from the configuration.

This part of the configuration allows you to send a message to all players.

```yaml
# Send a broadcast message when a player joins the server for the first time.
welcome-broadcast:
  enabled: false
  example-broadcast:
    first-time-only: true
    except-first-time: false
    delay: 0
    messages:
      - '&6Welcome &e%player_name% &6to the server!'
  # You can add more broadcast messages here.
```

This part of the configuration allows you to send a message only to the player who just logged in.

```yaml
# Send a private chat message when a player joins the server for the first time.
welcome-message:
  enabled: true
  example-message:
    first-time-only: true
    except-first-time: false
    delay: 0
    messages:
      - '&6Welcome &e%player_name% &6to the server!'
    # You can add more private messages here.

```

## Usage

Wait for a player to connect to the server!

## Placeholders

You can use all placeholders of [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/).

You need your own custom plugin ? Join us on our [discord](https://discord.gg/RZRvHd8puA).