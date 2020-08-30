package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventHandler implements Listener {

    Core core;

    public PlayerQuitEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (core.getConfig().getBoolean("connection-messages", true)) {
            e.setQuitMessage("§7[§c-§7] §c" + e.getPlayer().getName());
        } else {
            e.setQuitMessage("");
        }
        core.getAltManager().cleanUp(e.getPlayer().getUniqueId());

      //  String header = "§6Just§eFactions\n§a" + (Bukkit.getOnlinePlayers().size()-1) + "§7/§a500";
      //  String footer = "§eRanks, keys and MORE at §astore.justfactions.net";
       // for (Player player : Bukkit.getOnlinePlayers()) {
       //     Tab.setPlayerListHeader(player, header);
       //     Tab.setPlayerListFooter(player, footer);
        //}
    }

}
