package com.comze_instancelabs.mgskywars;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChestsConfig {

	private FileConfiguration arenaConfig = null;
	private File arenaFile = null;
	private JavaPlugin plugin = null;

	public ChestsConfig(JavaPlugin plugin) {
		this.plugin = plugin;
		this.getConfig().options().header("Just copy paste if you want more chests. The percentages for chests must add up to 100!");

		this.getConfig().addDefault("config.enabled", true);

		this.getConfig().addDefault("config.modes.chests", true);
		this.getConfig().addDefault("config.modes.items", false);
		
		// chest config

		this.getConfig().addDefault("config.chests.chest1.items", "wood*64;wood*64;wood*64;wood*64;arrow*64;diamond_pickaxe*1;wood*64%30");
		this.getConfig().addDefault("config.chests.chest1.percentage", 5);

		this.getConfig().addDefault("config.chests.chest2.items", "wood*64;arrow*64;iron_sword*1");
		this.getConfig().addDefault("config.chests.chest2.percentage", 20);

		this.getConfig().addDefault("config.chests.chest3.items", "wood*64;arrow*64");
		this.getConfig().addDefault("config.chests.chest3.percentage", 25);

		this.getConfig().addDefault("config.chests.chest4.items", "wood*64");
		this.getConfig().addDefault("config.chests.chest4.percentage", 50);
		
		// items config
		
		this.getConfig().addDefault("config.items.items1.items", "wood*64");
		this.getConfig().addDefault("config.items.items1.percentage", 50);
		
		this.getConfig().addDefault("config.items.items2.items", "arrow*64");
		this.getConfig().addDefault("config.items.items2.percentage", 25);
		
		this.getConfig().addDefault("config.items.items3.items", "diamond_pickaxe*1");
		this.getConfig().addDefault("config.items.items3.percentage", 15);
		
		this.getConfig().addDefault("config.items.items4.items", "iron_sword*1");
		this.getConfig().addDefault("config.items.items4.percentage", 45);

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}

	public FileConfiguration getConfig() {
		if (arenaConfig == null) {
			reloadConfig();
		}
		return arenaConfig;
	}

	public void saveConfig() {
		if (arenaConfig == null || arenaFile == null) {
			return;
		}
		try {
			getConfig().save(arenaFile);
		} catch (IOException ex) {

		}
	}

	public void reloadConfig() {
		if (arenaFile == null) {
			arenaFile = new File(plugin.getDataFolder(), "chests.yml");
		}
		arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);

		InputStream defConfigStream = plugin.getResource("chests.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
			arenaConfig.setDefaults(defConfig);
		}
	}

}
