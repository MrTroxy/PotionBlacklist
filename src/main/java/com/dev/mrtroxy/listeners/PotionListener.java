package com.dev.mrtroxy.listeners;

import com.dev.mrtroxy.PotionBlacklist;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class PotionListener implements Listener {
    private final PotionBlacklist plugin;

    public PotionListener(PotionBlacklist plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getType() == Material.POTION ||
                item.getType() == Material.SPLASH_POTION ||
                item.getType() == Material.LINGERING_POTION) {

            if (plugin.isBlacklisted(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You are not allowed to use potions!");

                // Creative feature: Small particle effect to indicate blocked action
                player.getWorld().spawnParticle(org.bukkit.Particle.SMOKE_NORMAL,
                        player.getLocation().add(0, 1, 0), 20, 0.5, 0.5, 0.5, 0.1);
            }
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;

        Player player = (Player) event.getEntity().getShooter();
        if (plugin.isBlacklisted(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are not allowed to use potions!");
        }
    }

    @EventHandler
    public void onPotionInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && (item.getType() == Material.POTION ||
                item.getType() == Material.SPLASH_POTION ||
                item.getType() == Material.LINGERING_POTION)) {

            if (plugin.isBlacklisted(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You are not allowed to use potions!");

                // Creative feature: Show what potion they tried to use
                PotionMeta meta = (PotionMeta) item.getItemMeta();
                player.sendMessage(ChatColor.GRAY + "Attempted to use: " +
                        ChatColor.ITALIC + meta.getBasePotionData().getType().toString());
            }
        }
    }
}