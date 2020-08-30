package net.justfactions.jfcore.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SellWand {

    public static ItemStack getSellWand(String creator) {
        ItemStack item = new ItemStack(Material.GOLD_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName("§3Sell Wand");
        meta.setLore(Arrays.asList(
                "§cInfo: §7Right click a container to sell its contents.",
                "§7You can buy these with §e/buy§7.",
                "§7You can win these in §6Legendary Crates§7.", "", "§7§oCreated by " + creator + " on " +  DateFormat.getDateTimeInstance(
                        DateFormat.LONG, DateFormat.LONG).format(new Date())));
        item.setItemMeta(meta);
        return item;
    }


    public static boolean isSellWand(ItemStack item) {
        if (item.getType() == Material.GOLD_AXE) {
            if (item.hasItemMeta()) {
                if (item.getItemMeta().hasLore()) {
                    if (item.getItemMeta().getLore().get(0).equalsIgnoreCase("§cInfo: §7Right click a container to sell its contents.")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static HashMap<Material,Double> getPrices() {
        HashMap<Material, Double> items = new HashMap<>();
        items.put(Material.IRON_INGOT, 7.0);
        items.put(Material.IRON_BLOCK, 7.0*9);
        items.put(Material.GOLD_INGOT, 50.0);
        items.put(Material.GOLD_BLOCK, 50.0*9);
        items.put(Material.DIAMOND, 100.0);
        items.put(Material.DIAMOND_BLOCK, 100.0*9);
        items.put(Material.EMERALD, 200.0);
        items.put(Material.EMERALD_BLOCK, 200.0*9);
        items.put(Material.QUARTZ, 0.5);
        items.put(Material.QUARTZ_BLOCK, 0.5*9);
        items.put(Material.REDSTONE, 1.0);
        items.put(Material.REDSTONE_BLOCK, 1.0*9);
        items.put(Material.COAL, 0.94);
        items.put(Material.COAL_BLOCK, 0.94*9);
        items.put(Material.INK_SACK, 5.0);
        items.put(Material.NETHER_STALK, 5.0);
        items.put(Material.SUGAR_CANE, 5.0);
        items.put(Material.WHEAT, 6.25);
        items.put(Material.PUMPKIN, 5.31);
        items.put(Material.MELON, 6.25);
        items.put(Material.CACTUS, 5.0);
        items.put(Material.ROTTEN_FLESH, 1.0);
        items.put(Material.BONE, 1.5);
        items.put(Material.SULPHUR, 2.0);
        items.put(Material.STRING, 1.88);
        items.put(Material.SPIDER_EYE, .5);
        items.put(Material.ENDER_PEARL, 4.0);
        items.put(Material.SLIME_BALL, 2.5);
        items.put(Material.PRISMARINE_CRYSTALS, 5.0);
        items.put(Material.PRISMARINE_SHARD, 3.0);
        items.put(Material.BLAZE_ROD, 6.0);
        items.put(Material.MAGMA_CREAM, 6.0);
        items.put(Material.GHAST_TEAR, 10.0);
        items.put(Material.LEATHER, 2.0);
        items.put(Material.RABBIT_HIDE, 2.0);
        items.put(Material.RABBIT_FOOT, 5.0);
        items.put(Material.INK_SACK, 5.0);
        items.put(Material.FEATHER, 2.0);
        items.put(Material.EGG, 2.0);
        items.put(Material.RED_ROSE, 0.5);
        return items;
    }
}
