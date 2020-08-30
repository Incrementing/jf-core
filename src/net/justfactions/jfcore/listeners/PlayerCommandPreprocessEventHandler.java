package net.justfactions.jfcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class PlayerCommandPreprocessEventHandler implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (!e.isCancelled()) {
            if (e.getMessage().toLowerCase().startsWith("/buy")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§6§l» §eThe store can be found at §a§ohttps://store.justfactions.net");
                e.getPlayer().closeInventory();
            } else if (e.getMessage().toLowerCase().startsWith("/report")) {
                e.getPlayer().sendMessage("§6§l» §eYou can report players by visiting §a§ohttps://justfactions.net/forums/report-players.25/");
                e.setCancelled(true);
            } else if (e.getMessage().toLowerCase().startsWith("/shop") || e.getMessage().toLowerCase().startsWith("/shopgui") || e.getMessage().toLowerCase().startsWith("/guishop")) {
                e.setCancelled(true);
                e.getPlayer().performCommand("shop");
            } else if (e.getMessage().toLowerCase().startsWith("/rules")) {
                e.getPlayer().sendMessage("§6§l» §eThe rules can be found at §a§ohttps://justfactions.net/threads/official-justfactions-rules.115/");
                e.setCancelled(true);
            } else if (
                    e.getMessage().toLowerCase().startsWith("/plugins") ||
                    e.getMessage().toLowerCase().startsWith("/pl")
                    ) {
                if (!e.getPlayer().isOp()) {
                    e.getPlayer().sendMessage("§4§l» §cSuch plugins, much list, very wow!");
                    e.setCancelled(true);
                }
            } else if (e.getMessage().toLowerCase().startsWith("/realonline")) {
                if (e.getPlayer().isOp()) {
                    List<String> ips = new ArrayList<>();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!ips.contains(p.getAddress().getHostString())) {
                            ips.add(p.getAddress().getHostString());
                        }
                    }
                    e.getPlayer().sendMessage("§6§l» §eOf the §7" + Bukkit.getOnlinePlayers().size() + "§e players online §7" + ips.size() + "§e are on unique IP addresses.");
                    e.setCancelled(true);
                }
            }
        }
    }
}
