package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.utils.RocketCreepers;
import net.justfactions.jfcore.utils.Shockwave;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("jf.rename")) {
                ItemStack item = p.getItemInHand().clone();
                if (item==null || item.getType() == Material.AIR) {
                    p.sendMessage("§4§l» §cYou can not re-name air!");
                } else {
                    if (args.length >= 1) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            if (sb.length() == 0) {
                                sb.append(args[i]);
                            } else {
                                sb.append(" ").append(args[i]);
                            }
                        }
                        if (sb.toString().replaceAll(" ", "").toLowerCase().contains("shockwave") || sb.toString().replaceAll(" ", "").toLowerCase().contains("rocketcreeper") || Shockwave.isShockwave(item) || RocketCreepers.isRocketCreeper(item)) {
                            p.sendMessage("§4§l» §cPlease do not try to \"sp00f\" Shockwaves/Rocket Creepers!");
                            return true;
                        }
                        if (item.getType() == Material.MOB_SPAWNER) {
                            p.sendMessage("§4§l» §cYou cannot rename spawners!");
                            return true;
                        }
                        if (item.getType() == Material.TRIPWIRE_HOOK) {
                            p.sendMessage("§4§l» §cYou cannot rename tripwire hooks!");
                            return true;
                        }

                        ItemMeta meta = item.getItemMeta();
                        String old;
                        if (meta.hasDisplayName()) {
                            old = meta.getDisplayName();
                        } else {
                            old = item.getType().name();
                        }
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', sb.toString()));
                        item.setItemMeta(meta);
                        p.setItemInHand(item);
                        p.updateInventory();
                        p.sendMessage("§2§l» §7" + old + "§a has been renamed to " + ChatColor.translateAlternateColorCodes('&', sb.toString()) + "§a.");
                    } else {
                        p.sendMessage("§4§l»§c Syntax: /rename <name>");
                    }
                }
            } else {
            p.sendMessage("§4§l» §cYou do not have permission to rename items! Please upgrade your rank with /buy to use this command.");
            }
        } else {
            commandSender.sendMessage("Fuck right off!");
        }

        return false;
    }
}
