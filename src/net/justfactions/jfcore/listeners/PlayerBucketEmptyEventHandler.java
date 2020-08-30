package net.justfactions.jfcore.listeners;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.Economy;
import net.justfactions.jfcore.manager.GenBucketsManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import java.math.BigDecimal;

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
public class PlayerBucketEmptyEventHandler implements Listener {

    @EventHandler
    public void onEmpty(PlayerBucketEmptyEvent e) {
        Block block = e.getBlockClicked().getRelative(e.getBlockFace());
        BigDecimal balance;
        try {
            balance = Economy.getMoneyExact(e.getPlayer().getName());
        } catch (UserDoesNotExistException e1) {
            balance = BigDecimal.ZERO;
        }
        if (e.getPlayer().getItemInHand().isSimilar(GenBucketsManager.getCobblestoneBukkit())) {
            e.setCancelled(true);
            if (balance.doubleValue()>=150) {
                try {
                    Economy.substract(e.getPlayer().getName(), new BigDecimal(150));
                } catch (UserDoesNotExistException | NoLoanPermittedException e1) {
                    e1.printStackTrace();
                }
                GenBucketsManager.generate(block, Material.COBBLESTONE);
                e.getPlayer().sendMessage("§8§l» §7GenBucket placed§c (-$150)§7!");
            } else {
                e.getPlayer().sendMessage("§4§l» §cYou do not have enough money to use this genbucket!");
            }
        }

        if (e.getPlayer().getItemInHand().isSimilar(GenBucketsManager.getObsidianBukkit())) {
            e.setCancelled(true);
            if (balance.doubleValue()>=2500) {
                try {
                    Economy.substract(e.getPlayer().getName(), new BigDecimal(2500));
                } catch (UserDoesNotExistException | NoLoanPermittedException e1) {
                    e1.printStackTrace();
                }
                GenBucketsManager.generate(block, Material.OBSIDIAN);
                e.getPlayer().sendMessage("§8§l» §7GenBucket placed§c (-$2500)§7!");
            } else {
                e.getPlayer().sendMessage("§4§l» §cYou do not have enough money to use this genbucket!");
            }
        }

        if (e.getPlayer().getItemInHand().isSimilar(GenBucketsManager.getSandBukkit())) {
            e.setCancelled(true);

            if (!block.getRelative(BlockFace.DOWN).getType().isSolid()) {
                e.getPlayer().sendMessage("§4§l» §cYou must place sand genbuckets on a solid block!");
                return;
            }

            if (balance.doubleValue()>=1250) {
                try {
                    Economy.substract(e.getPlayer().getName(), new BigDecimal(1250));
                } catch (UserDoesNotExistException | NoLoanPermittedException e1) {
                    e1.printStackTrace();
                }
                GenBucketsManager.generate(block, Material.SAND);
                e.getPlayer().sendMessage("§8§l» §7GenBucket placed§c (-$1250)§7!");
            } else {
                e.getPlayer().sendMessage("§4§l» §cYou do not have enough money to use this genbucket!");
            }
        }
    }
}
