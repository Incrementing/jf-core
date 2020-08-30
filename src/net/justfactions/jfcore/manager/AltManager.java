package net.justfactions.jfcore.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AltManager {

    HashMap<UUID, String> usersIPAddresses;

    public AltManager() {
        usersIPAddresses = new HashMap<>();
    }

    public void cleanUp() {
        usersIPAddresses.clear();
    }

    public void cleanUp(UUID uuid) {
        usersIPAddresses.remove(uuid);
    }

    public void handleJoinEvent(Player p) {
       usersIPAddresses.put(p.getUniqueId(), p.getAddress().getHostString());
    }

    public int getAltsOnline(String ipAddress) {
        int count = 0;
        for (Object value : usersIPAddresses.values().toArray()) {
            String ip = String.valueOf(value);
            if (ipAddress.equalsIgnoreCase(ip)) {
                count++;
            }
        }
        return count;
    }

    public String getAltNamesOnline(String ipAddress) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<UUID, String> user : usersIPAddresses.entrySet()) {
            if (user.getValue().equalsIgnoreCase(ipAddress)) {
                if (sb.length() == 0) {
                    sb.append(Bukkit.getOfflinePlayer(user.getKey()).getName());
                } else {
                    sb.append(", ").append(Bukkit.getOfflinePlayer(user.getKey()).getName());
                }
            }
        }
        return sb.toString();
    }

    public void handleReload() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            handleJoinEvent(p);
        }
    }

    public HashMap<UUID, String> getAlts() {
        return usersIPAddresses;
    }
}
