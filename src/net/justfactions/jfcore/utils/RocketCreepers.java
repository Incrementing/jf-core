package net.justfactions.jfcore.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RocketCreepers {

    public static ItemStack getRocketCreeper(int amount) {
        ItemStack item = new ItemStack(Material.MONSTER_EGG, amount, (short)50);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName("§3Rocket Creeper");
        meta.setLore(Arrays.asList(
                "§cInfo: §7Shoots upwards and explodes on contact.",
                "§7You can buy these with §e/buy§7.",
                "§7You can win these in §6Legendary Crates§7."));
        item.setItemMeta(meta);
        return item;
    }


    public static boolean isRocketCreeper(ItemStack item) {
        if (item.getType() == Material.MONSTER_EGG) {
            if (item.hasItemMeta()) {
                if (item.getItemMeta().hasLore()) {
                    if (item.getItemMeta().getLore().get(0).equalsIgnoreCase("§cInfo: §7Shoots upwards and explodes on contact.")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
