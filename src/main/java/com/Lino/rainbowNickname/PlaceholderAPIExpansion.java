package com.Lino.rainbowNickname;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    private final RainbowNickname plugin;

    public PlaceholderAPIExpansion(RainbowNickname plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "rainbownickname";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "";
        }

        // %rainbownickname_name% - Returns the formatted rainbow/animated name, or original name if no animation
        if (params.equalsIgnoreCase("name")) {
            return plugin.getNickManager().getFormattedName(player, plugin.getRainbowPhase());
        }

        // %rainbownickname_original% - Returns the original player name
        if (params.equalsIgnoreCase("original")) {
            return player.getName();
        }

        // %rainbownickname_has_animation% - Returns "true" or "false" if player has animation
        if (params.equalsIgnoreCase("has_animation")) {
            return plugin.getNickManager().hasNick(player) ? "true" : "false";
        }

        // %rainbownickname_animation_type% - Returns the animation type name, or "none"
        if (params.equalsIgnoreCase("animation_type")) {
            AnimationType type = plugin.getNickManager().getAnimation(player);
            return type != null ? type.name() : "none";
        }

        return null;
    }
}
