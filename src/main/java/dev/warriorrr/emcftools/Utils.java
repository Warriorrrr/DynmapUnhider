package dev.warriorrr.emcftools;

import java.awt.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.dynmap.DynmapAPI;

import static dev.warriorrr.emcftools.EMCFTools.prefix;

import net.md_5.bungee.api.ChatColor;

public class Utils {
    /**
     * Returns a nice looking gradient between 2 colors, applied to a
     * @param string The string to apply the gradient to.
     * @param from The color at the start of the string.
     * @param to The color at the end of the string.
     */
    public static String gradient(String string, Color from, Color to) {
        int l = string.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            sb.append(ChatColor.of(new Color(
                    (from.getRed() + (i * (1.0F / l) * (to.getRed() - from.getRed()))) / 255,
                    (from.getGreen() + (i * (1.0F / l) * (to.getGreen() - from.getGreen()))) / 255,
                    (from.getBlue() + (i * (1.0F / l) * (to.getBlue() - from.getBlue()))) / 255
            )));
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    public static boolean isHiddenOnDynmap(Player player) {
        DynmapAPI dynmapApi = ((DynmapAPI) Bukkit.getPluginManager().getPlugin("dynmap"));
        return !dynmapApi.getPlayerVisbility(player);
    }

    public static boolean canBeHidden(Player player) {
        return player.hasPermission("dynmap.hide.self");
    }

    public static void hidePlayer(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "dynmap hide " + player.getName());
        player.sendMessage(prefix + ChatColor.AQUA + " You are now hidden on Dynmap.");
    }

    public static void showPlayer(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "dynmap show " + player.getName());
        player.sendMessage(prefix + ChatColor.AQUA + " You are now shown on Dynmap.");
    }
}
