package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.utils.Shockwave;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ShockwaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.isOp()) {
                if (args.length == 0) {
                    p.sendMessage("§6§L» §eShowing Shockwave commands:");
                    p.sendMessage("§e/shockwave give <name> [creator] - Give a user a shockwave pickaxe");
                } else {
                    String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                    switch (args[0].toLowerCase()) {
                        case "give":
                            if (subArgs.length == 1) {
                                Player target = Bukkit.getPlayer(subArgs[0]);
                                if (target!=null) {
                                    ItemStack shockwave = Shockwave.getShockwave(p.getName());
                                    if (target.getInventory().firstEmpty()!=-1) {
                                        target.getInventory().addItem(shockwave);
                                        p.sendMessage("§2§l» §aShockwave added to §7" + target.getName() + "§a's inventory!");
                                    } else {
                                        p.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                    }
                                } else {
                                    p.sendMessage("§7§l» §cUnable to find a player by that username!");
                                }
                            } else if (subArgs.length == 2) {
                                Player target = Bukkit.getPlayer(subArgs[0]);
                                if (target!=null) {
                                    ItemStack shockwave = Shockwave.getShockwave(subArgs[1].replaceAll("_", " "));
                                    if (target.getInventory().firstEmpty()!=-1) {
                                        target.getInventory().addItem(shockwave);
                                        p.sendMessage("§2§l» §aShockwave added to §7" + target.getName() + "§a's inventory!");
                                    } else {
                                        p.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                    }
                                } else {
                                    p.sendMessage("§7§l» §cUnable to find a player by that username!");
                                }
                            } else {
                                p.sendMessage("§4§l» §cIncorrect Syntax! Try: /shockwave give <name> [creator]");
                            }
                            break;

                        default:
                            p.performCommand("shockwave");
                            break;
                    }
                }
            } else {
                p.sendMessage("§4§l» §cYou do not have permission for this command!");
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage("§6§L» §eShowing Shockwave commands:");
                sender.sendMessage("§e/shockwave give <name> [creator] - Give a user a shockwave pickaxe");
            } else {
                String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                switch (args[0].toLowerCase()) {
                    case "give":
                        if (subArgs.length == 1) {
                            Player target = Bukkit.getPlayer(subArgs[0]);
                            if (target!=null) {
                                ItemStack shockwave = Shockwave.getShockwave(sender.getName());
                                if (target.getInventory().firstEmpty()!=-1) {
                                    target.getInventory().addItem(shockwave);
                                    sender.sendMessage("§2§l» §aShockwave added to §7" + target.getName() + "§a's inventory!");
                                } else {
                                    sender.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                }
                            } else {
                                sender.sendMessage("§7§l» §cUnable to find a player by that username!");
                            }
                        } else if (subArgs.length == 2) {
                            Player target = Bukkit.getPlayer(subArgs[0]);
                            if (target!=null) {
                                ItemStack shockwave = Shockwave.getShockwave(subArgs[1].replaceAll("_", " "));
                                if (target.getInventory().firstEmpty()!=-1) {
                                    target.getInventory().addItem(shockwave);
                                    sender.sendMessage("§2§l» §aShockwave added to §7" + target.getName() + "§a's inventory!");
                                } else {
                                    sender.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                }
                            } else {
                                sender.sendMessage("§7§l» §cUnable to find a player by that username!");
                            }
                        } else {
                            sender.sendMessage("§4§l» §cIncorrect Syntax! Try: /shockwave give <name> [creator]");
                        }
                        break;

                    default:
                        sender.sendMessage("§6§L» §eShowing Shockwave commands:");
                        sender.sendMessage("§e/shockwave give <name> [creator] - Give a user a shockwave pickaxe");
                        break;
                }
            }
        }
        return false;
    }
}
