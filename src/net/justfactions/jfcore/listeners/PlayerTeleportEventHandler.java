package net.justfactions.jfcore.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportEventHandler implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Location location = e.getTo();
        location.setX(location.getBlockX() + .5);
        location.setZ(location.getBlockZ() + .5);
        e.setTo(location);
    }

}
