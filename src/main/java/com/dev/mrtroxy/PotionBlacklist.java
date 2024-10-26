package com.dev.mrtroxy;

import com.dev.mrtroxy.commands.PotionCommand;
import com.dev.mrtroxy.listeners.PotionListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PotionBlacklist extends JavaPlugin {
    private File blacklistFile;
    private FileConfiguration blacklistConfig;
    private Set<UUID> blacklistedPlayers;

    @Override
    public void onEnable() {
        // Initialize blacklist storage
        blacklistedPlayers = new HashSet<>();

        // Create default config
        saveDefaultConfig();

        // Initialize blacklist
        loadBlacklist();

        // Register commands
        getCommand("potion").setExecutor(new PotionCommand(this));

        // Register events
        getServer().getPluginManager().registerEvents(new PotionListener(this), this);

        // Log startup
        getLogger().info("PotionBlacklist has been enabled!");
    }

    @Override
    public void onDisable() {
        if (blacklistConfig != null) {
            saveBlacklist();
        }
        getLogger().info("PotionBlacklist has been disabled!");
    }

    private void loadBlacklist() {
        blacklistFile = new File(getDataFolder(), "blacklist.yml");
        if (!blacklistFile.exists()) {
            blacklistFile.getParentFile().mkdirs();
            try {
                blacklistFile.createNewFile();
                blacklistConfig = YamlConfiguration.loadConfiguration(blacklistFile);
                blacklistConfig.set("blacklisted-players", new ArrayList<String>());
                blacklistConfig.save(blacklistFile);
            } catch (IOException e) {
                getLogger().severe("Could not create blacklist.yml!");
                e.printStackTrace();
                return;
            }
        }

        blacklistConfig = YamlConfiguration.loadConfiguration(blacklistFile);
        for (String uuidStr : blacklistConfig.getStringList("blacklisted-players")) {
            try {
                blacklistedPlayers.add(UUID.fromString(uuidStr));
            } catch (IllegalArgumentException e) {
                getLogger().warning("Invalid UUID in blacklist.yml: " + uuidStr);
            }
        }
    }

    public void saveBlacklist() {
        if (blacklistConfig == null || blacklistFile == null) return;

        blacklistConfig.set("blacklisted-players",
                blacklistedPlayers.stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()));
        try {
            blacklistConfig.save(blacklistFile);
        } catch (IOException e) {
            getLogger().severe("Could not save blacklist file!");
            e.printStackTrace();
        }
    }

    public boolean isBlacklisted(UUID playerUUID) {
        return blacklistedPlayers.contains(playerUUID);
    }

    public void addToBlacklist(UUID playerUUID) {
        blacklistedPlayers.add(playerUUID);
        saveBlacklist();
    }

    public void removeFromBlacklist(UUID playerUUID) {
        blacklistedPlayers.remove(playerUUID);
        saveBlacklist();
    }
}