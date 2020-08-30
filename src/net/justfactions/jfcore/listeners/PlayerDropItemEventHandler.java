package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.manager.GenBucketsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

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
public class PlayerDropItemEventHandler implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().isSimilar(GenBucketsManager.getCobblestoneBukkit())||e.getItemDrop().getItemStack().isSimilar(GenBucketsManager.getSandBukkit())||e.getItemDrop().getItemStack().isSimilar(GenBucketsManager.getObsidianBukkit())) {
            e.getItemDrop().remove();
            e.getPlayer().sendMessage("§8§l» §7GenBucket despawned!");
        }
    }
}
