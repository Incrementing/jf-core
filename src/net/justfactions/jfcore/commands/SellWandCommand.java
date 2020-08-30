package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.utils.SellWand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SellWandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.isOp()) {
                if (args.length == 0) {
                    p.sendMessage("§6§L» §eShowing Sell Wand commands:");
                    p.sendMessage("§e/sellwand give <name> [creator] - Give a user a sell wand");
                } else {
                    String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                    switch (args[0].toLowerCase()) {
                        case "give":
                            if (subArgs.length == 1) {
                                Player target = Bukkit.getPlayer(subArgs[0]);
                                if (target!=null) {
                                    ItemStack sellwand = SellWand.getSellWand(p.getName());
                                    if (target.getInventory().firstEmpty()!=-1) {
                                        target.getInventory().addItem(sellwand);
                                        p.sendMessage("§2§l» §aSell Wand added to §7" + target.getName() + "§a's inventory!");
                                    } else {
                                        p.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                    }
                                } else {
                                    p.sendMessage("§7§l» §cUnable to find a player by that username!");
                                }
                            } else if (subArgs.length == 2) {
                                Player target = Bukkit.getPlayer(subArgs[0]);
                                if (target!=null) {
                                    ItemStack sellwand = SellWand.getSellWand(subArgs[1].replaceAll("_", " "));
                                    if (target.getInventory().firstEmpty()!=-1) {
                                        target.getInventory().addItem(sellwand);
                                        p.sendMessage("§2§l» §aSell Wand added to §7" + target.getName() + "§a's inventory!");
                                    } else {
                                        p.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                    }
                                } else {
                                    p.sendMessage("§7§l» §cUnable to find a player by that username!");
                                }
                            } else {
                                p.sendMessage("§4§l» §cIncorrect Syntax! Try: /sellwand give <name> [creator]");
                            }
                            break;

                        default:
                            p.performCommand("sellwand");
                            break;
                    }
                }
            } else {
                p.sendMessage("§4§l» §cYou do not have permission for this command!");
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage("§6§L» §eShowing Sell Wand commands:");
                sender.sendMessage("§e/sellwand give <name> [creator] - Give a user a sell wand");
            } else {
                String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                switch (args[0].toLowerCase()) {
                    case "give":
                        if (subArgs.length == 1) {
                            Player target = Bukkit.getPlayer(subArgs[0]);
                            if (target!=null) {
                                ItemStack sellwand = SellWand.getSellWand(sender.getName());
                                if (target.getInventory().firstEmpty()!=-1) {
                                    target.getInventory().addItem(sellwand);
                                    sender.sendMessage("§2§l» §aSell Wand added to §7" + target.getName() + "§a's inventory!");
                                } else {
                                    sender.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                }
                            } else {
                                sender.sendMessage("§7§l» §cUnable to find a player by that username!");
                            }
                        } else if (subArgs.length == 2) {
                            Player target = Bukkit.getPlayer(subArgs[0]);
                            if (target!=null) {
                                ItemStack sellwand = SellWand.getSellWand(subArgs[1].replaceAll("_", " "));
                                if (target.getInventory().firstEmpty()!=-1) {
                                    target.getInventory().addItem(sellwand);
                                    sender.sendMessage("§2§l» §aSell Wand added to §7" + target.getName() + "§a's inventory!");
                                } else {
                                    sender.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                }
                            } else {
                                sender.sendMessage("§7§l» §cUnable to find a player by that username!");
                            }
                        } else {
                            sender.sendMessage("§4§l» §cIncorrect Syntax! Try: /sellwand give <name> [creator]");
                        }
                        break;

                    default:
                        sender.sendMessage("§6§L» §eShowing Sell Wand commands:");
                        sender.sendMessage("§e/sellwand give <name> [creator] - Give a user a sell wand");
                        break;
                }
            }
        }
        return false;
    }
}
