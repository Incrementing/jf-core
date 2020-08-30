package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.manager.GenBucketsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * **************************************************************************************
 * Copyright Molten Rock Ltd (c) 2017. All Right Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of Molten Rock Ltd. Distribution, taking snippets, or
 * claiming any contents as your own will break the terms of licence, and void any
 * agreements with you, the third party.
 * Regards Molten Rock Ltd - @MoltenRockLtd - https://moltenrock.io
 * ***************************************************************************************
 */
public class InventoryClickEventHandler implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getName().equalsIgnoreCase("§3GenBuckets:")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.COBBLESTONE) {
                if (!p.getInventory().contains(GenBucketsManager.getCobblestoneBukkit())) {
                p.getInventory().addItem(GenBucketsManager.getCobblestoneBukkit());
            }
                p.closeInventory();
                p.sendMessage("§8§l» §7You've been given a cobblestone genbucket!");
            } else if (e.getCurrentItem().getType() == Material.OBSIDIAN) {
                if (!p.getInventory().contains(GenBucketsManager.getObsidianBukkit())) {
                    p.getInventory().addItem(GenBucketsManager.getObsidianBukkit());
                }
                p.closeInventory();
                p.sendMessage("§8§l» §7You've been given a obsidian genbucket!");
            } else if (e.getCurrentItem().getType() == Material.SAND) {
                if (!p.getInventory().contains(GenBucketsManager.getSandBukkit())) {
                    p.getInventory().addItem(GenBucketsManager.getSandBukkit());
                }
                p.closeInventory();
                p.sendMessage("§8§l» §7You've been given a sand genbucket!");
            }
        }
    }
}
