package net.justfactions.jfcore.utils;

import net.justfactions.jfcore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reclaim {

    private FileConfiguration config = null;
    private File file = null;
    private Core core;

    public Reclaim(Core core) {
        this.core = core;
    }

    private void reload() {
        if (file == null) {
            file = new File(core.getDataFolder(), "reclaim.yml");
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get() {
        if (config == null) {
            reload();
        }
        return config;
    }

    public void save() {
        if (config == null || file == null) {
            return;
        }
        try {
            get().save(file);
        } catch (IOException ex) {
            Logger.getLogger("Minecraft").log(Level.SEVERE, "[JustLobby] Could not save player data to reclaim.yml", ex);
        }
    }

    public void delete() {
        file.delete();
        file = null;
        config = null;
    }
}
