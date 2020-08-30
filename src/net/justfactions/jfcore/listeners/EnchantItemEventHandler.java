package net.justfactions.jfcore.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

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
public class EnchantItemEventHandler implements Listener {

    @EventHandler
    public void onEncant(EnchantItemEvent e) {
        if (e.getEnchantsToAdd().containsKey(Enchantment.DEPTH_STRIDER)) {
            e.getEnchantsToAdd().remove(Enchantment.DEPTH_STRIDER);
        }
    }
}
