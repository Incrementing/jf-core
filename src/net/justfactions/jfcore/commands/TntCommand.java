package net.justfactions.jfcore.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class TntCommand implements CommandExecutor {

    HashMap<UUID, Long> lastUsed = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("jf.tnt")) {
                Block eye = p.getTargetBlock((Set<Material>) null, 25);
                if (eye != null) {
                    if (!eye.isLiquid()) {
                        eye = eye.getRelative(BlockFace.valueOf(get(p)));
                    }

                    if (lastUsed.containsKey(p.getUniqueId())) {
                        int passed = (int) (System.currentTimeMillis() - lastUsed.get(p.getUniqueId())) / 1000;
                        if (passed >= 300) {
                            eye.getWorld().spawnEntity(eye.getLocation(), EntityType.PRIMED_TNT);
                            p.sendMessage("§6§l» §eTnt launched, clear the area!");
                            lastUsed.put(p.getUniqueId(), System.currentTimeMillis());
                        } else {
                            p.sendMessage("§4§l» §cYou must wait §7" + time(300 - passed) + "§c before using /tnt again!");
                        }
                    } else {
                        eye.getWorld().spawnEntity(eye.getLocation(), EntityType.PRIMED_TNT);
                        p.sendMessage("§6§l» §eTnt launched, clear the area!");
                        lastUsed.put(p.getUniqueId(), System.currentTimeMillis());
                    }
                } else {
                    p.sendMessage("§4§ll» §cThere is no block in your line of sight!");
                }
            } else {
                p.sendMessage("§4§l» §cYour rank doesn't have /tnt (upgrade with /buy).");
            }
        } else {
            sender.sendMessage("You're not a player :/");
        }
        return false;
    }

    private String time(int secs) {
        int seconds = secs % 60;
        int minutes = secs / 60;

        if (minutes >= 1) {
            return minutes + " minute" + (minutes>1 ? "s" : "") + " " + seconds + " second" + (seconds>1 ? "s" : "");
        }
        return seconds + " second" + (seconds>1 ? "s" : "");
    }

    public static String get(Player player) {
        float yaw = player.getLocation().getYaw();
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw >= 315 || yaw < 45) {
            return "NORTH";
        } else if (yaw < 135) {
            return "EAST";
        } else if (yaw < 225) {
            return "SOUTH";
        } else if (yaw < 315) {
            return "WEST";
        }
        return "SOUTH";
    }
}
