package net.justfactions.jfcore.manager;

import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

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
public class GenBucketsManager {

    private static Inventory inventory;
    private static HashMap<String, Integer> keys = new HashMap<>();
    private static HashMap<String, Block> locations = new HashMap<>();

    public static Inventory getInventory() {
        if (inventory == null) {
            inventory = Bukkit.createInventory(null, 9, "§3GenBuckets:");
        }

        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE);
        ItemMeta cobblestoneM = cobblestone.getItemMeta();
        cobblestoneM.setDisplayName("§eCobblestone");
        cobblestoneM.setLore(Arrays.asList(
                "§cCost: §e$150",
                "",
                "§cInfo:",
                "§7- Place to generate cobblestone walls.",
                "§7- Walls generate down until they hit a soild block.",
                "§7- You'll be charged everytime you place a genbucket.",
                "",
                "§7§oClick to get a cobblestone genbucket."
        ));
        cobblestone.setItemMeta(cobblestoneM);
        inventory.setItem(2, cobblestone);

        ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
        ItemMeta obsidianM = obsidian.getItemMeta();
        obsidianM.setDisplayName("§eObsidian");
        obsidianM.setLore(Arrays.asList(
                "§cCost: §e$2500",
                "",
                "§cInfo:",
                "§7- Place to generate obsidian walls.",
                "§7- Walls generate down until they hit a soild block.",
                "§7- You'll be charged everytime you place a genbucket.",
                "",
                "§7§oClick to get a obsidian genbucket."
        ));
        obsidian.setItemMeta(obsidianM);
        inventory.setItem(4, obsidian);

        ItemStack sand = new ItemStack(Material.SAND);
        ItemMeta sandM = sand.getItemMeta();
        sandM.setDisplayName("§eSand");
        sandM.setLore(Arrays.asList(
                "§cCost: §e$1250",
                "",
                "§cInfo:",
                "§7- Place to generate sand walls.",
                "§7- Walls generate up until build limit or they hit a solid block.",
                "§7- You'll be charged everytime you place a genbucket.",
                "",
                "§7§oClick to get a sand genbucket."
        ));
        sand.setItemMeta(sandM);
        inventory.setItem(6, sand);
        
        return inventory;
    }

    public static ItemStack getCobblestoneBukkit() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eCobblestone");
        meta.setLore(Arrays.asList(
                "§cCost: §e$150",
                "",
                "§cInfo:",
                "§7- Place to generate cobblestone walls.",
                "§7- Walls generate down until they hit a soild block.",
                "§7- You'll be charged everytime you place a genbucket.",
                "",
                "§7§oDrop this bucket to get rid of it!"
        ));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getObsidianBukkit() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eObsidian");
        meta.setLore(Arrays.asList(
                "§cCost: §e$2500",
                "",
                "§cInfo:",
                "§7- Place to generate obsidian walls.",
                "§7- Walls generate down until they hit a soild block.",
                "§7- You'll be charged everytime you place a genbucket.",
                "",
                "§7§oDrop this bucket to get rid of it!"
        ));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSandBukkit() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eSand");
        meta.setLore(Arrays.asList(
                "§cCost: §e$1250",
                "",
                "§cInfo:",
                "§7- Place to generate sand walls.",
                "§7- Walls generate up until build limit or they hit a solid block.",
                "§7- You'll be charged everytime you place a genbucket.",
                "",
                "§7§oDrop this bucket to get rid of it!"
        ));
        item.setItemMeta(meta);
        return item;
    }

    @SuppressWarnings("Duplicates")
    public static void generate(Block block, Material material) {
        Core core = (Core) Bukkit.getPluginManager().getPlugin("AresFactions");

        if (material == Material.SAND) {
            keys.put(block.getLocation().toString()+material.name(), core.getServer().getScheduler().runTaskTimer(core, () -> {
                if (locations.containsKey(block.getLocation().toString()+material.name())) {
                    Block next = locations.get(block.getLocation().toString()+material.name()).getRelative(BlockFace.UP);
                    if (!next.getType().isSolid() && next.getLocation().getY() < block.getWorld().getMaxHeight()) {
                        next.setType(material);
                        next.getState().update();
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playEffect(next.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
                        }
                        locations.put(block.getLocation().toString()+material.name(), next);
                    } else {
                        cancel(block.getLocation().toString()+material.name());
                    }
                } else {
                    if (!block.getType().isSolid() && block.getLocation().getY() < block.getWorld().getMaxHeight()) {
                        block.setType(material);
                        block.getState().update();
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playEffect(block.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
                        }
                        locations.put(block.getLocation().toString()+material.name(), block);
                    } else {
                        cancel(block.getLocation().toString()+material.name());
                    }
                }
            }, 0L, 10L).getTaskId());
        } else {
            keys.put(block.getLocation().toString()+material.name(), core.getServer().getScheduler().runTaskTimer(core, () -> {
                if (locations.containsKey(block.getLocation().toString()+material.name())) {
                    Block next = locations.get(block.getLocation().toString()+material.name()).getRelative(BlockFace.DOWN);
                    if (!next.getType().isSolid()) {
                        next.setType(material);
                        next.getState().update();
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playEffect(next.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
                        }
                        locations.put(block.getLocation().toString()+material.name(), next);
                    } else {
                        cancel(block.getLocation().toString()+material.name());
                    }
                } else {
                    if (!block.getType().isSolid()) {
                        block.setType(material);
                        block.getState().update();
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playEffect(block.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
                        }
                        locations.put(block.getLocation().toString()+material.name(), block);
                    } else {
                        cancel(block.getLocation().toString()+material.name());
                    }
                }
            }, 0L, 10L).getTaskId());
        }
    }

    private static void cancel(String key) {
        if (keys.containsKey(key)) {
            Bukkit.getScheduler().cancelTask(keys.get(key));
            keys.remove(key);
            locations.remove(key);
        }
    }
}
