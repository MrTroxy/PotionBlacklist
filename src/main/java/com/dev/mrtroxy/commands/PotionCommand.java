package com.dev.mrtroxy.commands;

import com.dev.mrtroxy.PotionBlacklist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class PotionCommand implements CommandExecutor {
    private final PotionBlacklist plugin;

    public PotionCommand(PotionBlacklist plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("potion.blacklist")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length < 2 || !args[0].equalsIgnoreCase("blacklist")) {
            sender.sendMessage(ChatColor.RED + "Usage: /potion blacklist <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        if (target.hasPermission("potion.blacklist.exempt")) {
            sender.sendMessage(ChatColor.RED + "This player cannot be blacklisted!");
            return true;
        }

        if (plugin.isBlacklisted(target.getUniqueId())) {
            plugin.removeFromBlacklist(target.getUniqueId());
            sender.sendMessage(ChatColor.GREEN + target.getName() + " has been removed from the potion blacklist!");
            target.sendMessage(ChatColor.GREEN + "You can now use potions again!");
        } else {
            plugin.addToBlacklist(target.getUniqueId());
            sender.sendMessage(ChatColor.RED + target.getName() + " has been added to the potion blacklist!");
            target.sendMessage(ChatColor.RED + "You have been restricted from using potions!");

            // Creative feature: Remove any active potion effects
            target.getActivePotionEffects().forEach(effect -> target.removePotionEffect(effect.getType()));
        }

        return true;
    }
}