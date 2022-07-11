package io.github.zohiu.smplyblockcatapult;

import com.tchristofferson.configupdater.ConfigUpdater;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;


public final class SMPlyBlockCatapult extends JavaPlugin implements Listener {

    public static SMPlyBlockCatapult instance;

    public Material block;
    public boolean particles;
    public boolean fireworks;
    public boolean falldamage;

    public Double force;

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();

        this.getServer().getPluginManager().registerEvents(new WalkOnBlock(), this);
        this.getServer().getPluginManager().registerEvents(new FallDamage(), this);

        this.getCommand("smplyblockcatapult").setExecutor(new ReloadCommand());

        this.getLogger().log(Level.INFO, ChatColor.GREEN + "Enabled.");
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, ChatColor.RED + "Disabled.");
    }

    public String loadConfig() {
        // Config-Updater to not worry about config weirdness.
        // https://github.com/tchristofferson/Config-Updater
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(this, "config.yml", configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();

        // load config to var
        FileConfiguration config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        // set vars from config
        block = Material.matchMaterial(Objects.requireNonNull(config.getString("block")));
        falldamage = config.getBoolean("falldamage_on_block");
        particles = config.getBoolean("particles");
        fireworks = config.getBoolean("fireworks");

        force = config.getDouble("force");

        if (!Objects.requireNonNull(block).isBlock()) {
            block = Material.EMERALD_BLOCK;
            return ChatColor.RED + block.toString() + " is not a valid block! Defaulting to emerald_block.";
        }

        return ChatColor.GREEN + "Successfully reloaded!";
    }

    public static SMPlyBlockCatapult getInstance() {
        return instance;
    }
}
