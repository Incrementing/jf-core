package net.justfactions.jfcore.listeners;

import com.arespvp.api.events.OverlayRankChangeEvent;
import com.arespvp.api.events.RankChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
public class OverlayRankChangeEventHandler implements Listener {

    @EventHandler
    public void onRankChange(OverlayRankChangeEvent e) {
        if (e.getNewRank() != null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + e.getUser().getOfflinePlayer().getName() + " group add " + e.getNewRank().name());
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + e.getUser().getOfflinePlayer().getName() + " group set " + e.getUser().getRank().name());
        }
    }
}
