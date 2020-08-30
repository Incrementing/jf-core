package net.justfactions.jfcore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class PrepareItemCraftEventHandler implements Listener {

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if (e.getRecipe().getResult().getType() == Material.HOPPER) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            for(HumanEntity he : e.getViewers()) {
                if(he instanceof Player) {
                    he.sendMessage("§4§l» §cYou cannot craft hoppers, you must buy them from /shop!");
                }
            }
        }
    }
}
