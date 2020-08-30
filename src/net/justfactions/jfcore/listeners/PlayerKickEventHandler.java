package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickEventHandler implements Listener {

    Core core;

    public PlayerKickEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if (core.getConfig().getBoolean("connection-messages", true)) {
            e.setLeaveMessage("§7[§c-§7] §c" + e.getPlayer().getName());
        } else {
            e.setLeaveMessage("");
        }
        core.getAltManager().cleanUp(e.getPlayer().getUniqueId());
    }
}
