package net.justfactions.jfcore.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeEventHandler implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (e.getEntity() != null) {
            if (e.getEntity().getType() == EntityType.PRIMED_TNT) {
                if (e.getLocation().getY() >= 255) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
