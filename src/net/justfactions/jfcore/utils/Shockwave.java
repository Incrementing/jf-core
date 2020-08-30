package net.justfactions.jfcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Shockwave {

    public static ItemStack getShockwave(String maker) {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        meta.setDisplayName("§3Shockwave Pickaxe");

        meta.setLore(Arrays.asList("", "§cWarning: §7Mines a 3x3 area", "§7You can buy these with §e/buy§7.", "", "§7§oCreated by " + maker + " on " +  DateFormat.getDateTimeInstance(
                DateFormat.LONG, DateFormat.LONG).format(new Date())));
        item.setItemMeta(meta);
        return item;
    }


    public static boolean isShockwave(ItemStack item) {
        if (item.getType() == Material.DIAMOND_PICKAXE) {
            if (item.hasItemMeta()) {
                if (item.getItemMeta().hasLore()) {
                    if (item.getItemMeta().getLore().get(1).equalsIgnoreCase("§cWarning: §7Mines a 3x3 area")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean canBreak(Block block) {
        List<String> rawMaterials = Bukkit.getServer().getPluginManager().getPlugin("AresFactions").getConfig().getStringList("shockwave.ignore");
        List<Material> materials = new ArrayList<Material>();
        for (String s : rawMaterials) {
            try {
                materials.add(Material.valueOf(s.toUpperCase()));
            } catch (Exception ex) {
                System.out.print("The material " + s.toUpperCase() + " doesn't exist... failed to check if it's ignored");
            }
        }
        return (!(materials.contains(block.getType())));
    }
}
