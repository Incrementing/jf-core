package net.justfactions.jfcore.utils;

import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NicknameManager {

   public class NicknameStorage {
       private FileConfiguration config = null;
       private File file = null;
       private Core core;

       public NicknameStorage(Core core) {
           this.core = core;
       }

       private void reload() {
           if (file == null) {
               file = new File(core.getDataFolder(), "nicknames.yml");
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
               Logger.getLogger("Minecraft").log(Level.SEVERE, "[JustLobby] Could not save player data to nicknames.yml", ex);
           }
       }

       public void delete() {
           file.delete();
           file = null;
           config = null;
       }
   }


   NicknameStorage storage;

   public NicknameManager(Core core) {
       storage = new NicknameStorage(core);
   }


   public String getNickname(UUID uuid) {
       try {
           return storage.get().getString("nicknames." + uuid.toString(), Bukkit.getPlayer(uuid).getName());
       } catch (Exception ex) {
           return Bukkit.getPlayer(uuid).getName();
       }
   }

   public void setNickname(UUID uuid, String nickname) {
       storage.get().set("nicknames." + uuid.toString(), nickname);
       storage.save();
   }
}
