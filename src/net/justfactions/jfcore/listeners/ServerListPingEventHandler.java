package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingEventHandler implements Listener {

    Core core;

    public ServerListPingEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMotd(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("serverlistpingevent.motd.1", "Failed to read from /plugins/JFCore/config.yml"))
                + "\n" +
                ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("serverlistpingevent.motd.2", "Contact a member of staff to fix this!"))
        );
        e.setMaxPlayers(core.getConfig().getInt("serverlistpingevent.players.max"));
    }
}
