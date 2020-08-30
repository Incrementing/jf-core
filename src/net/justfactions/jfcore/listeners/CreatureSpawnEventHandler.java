package net.justfactions.jfcore.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnEventHandler implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        if (e.getEntity() instanceof Wither) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.BUILD_WITHER) {
                if (((Wither) e.getEntity()).getTarget() instanceof HumanEntity) {
                    ((Wither) e.getEntity()).getTarget().sendMessage("§4§l» §cWither has been removed, wither raiding is bannable.");
                }
            }
            e.getEntity().remove();
            e.setCancelled(true);
        }
    }
}
