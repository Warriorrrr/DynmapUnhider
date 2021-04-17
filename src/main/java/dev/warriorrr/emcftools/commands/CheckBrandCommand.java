package dev.warriorrr.emcftools.commands;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import static dev.warriorrr.emcftools.EMCFTools.prefix;
import static dev.warriorrr.emcftools.EMCFTools.clientBrands;

public class CheckBrandCommand implements CommandExecutor, TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("emcftools.checkbrand"))
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(playerName -> playerName.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("emcftools.checkbrand")) {
                sender.sendMessage(prefix + ChatColor.RED + " You do not have enough permissions to use this command.");
                return true;
            }
        }

        if (args.length < 1 || Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(prefix + ChatColor.RED + " Unknown or invalid player.");
            return true;
        }

        Player playerToCheck = Bukkit.getPlayer(args[0]);
        String brandName = clientBrands.get(playerToCheck.getUniqueId()) != null ? clientBrands.get(playerToCheck.getUniqueId()) : "undefined";
        sender.sendMessage(String.format(prefix + ChatColor.GRAY + " %s's client brand name is '%s'", playerToCheck.getName(), brandName));
        return true;
    }
}
