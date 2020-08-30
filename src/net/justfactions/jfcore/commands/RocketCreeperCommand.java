package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.utils.RocketCreepers;
import net.justfactions.jfcore.utils.Shockwave;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class RocketCreeperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.isOp()) {
                if (args.length == 0) {
                    p.sendMessage("§6§L» §eShowing RocketCreeper commands:");
                    p.sendMessage("§e/rocketcreeper give <name> [amount] - Give a user a Rocket Creeper");
                } else {
                    String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                    switch (args[0].toLowerCase()) {
                        case "give":
                            if (subArgs.length == 1) {
                                Player target = Bukkit.getPlayer(subArgs[0]);
                                if (target!=null) {
                                    ItemStack rocketcreeper = RocketCreepers.getRocketCreeper(1);
                                    if (target.getInventory().firstEmpty()!=-1) {
                                        target.getInventory().addItem(rocketcreeper);
                                        p.sendMessage("§2§l» §aRocketCreeper added to §7" + target.getName() + "§a's inventory!");
                                    } else {
                                        p.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                    }
                                } else {
                                    p.sendMessage("§7§l» §cUnable to find a player by that username!");
                                }
                            } else if (subArgs.length == 2) {
                                Player target = Bukkit.getPlayer(subArgs[0]);
                                if (target!=null) {
                                    try {
                                        int amount = Integer.parseInt(subArgs[1]);
                                        ItemStack rocketcreeper = RocketCreepers.getRocketCreeper(amount);
                                        if (target.getInventory().firstEmpty()!=-1) {
                                            target.getInventory().addItem(rocketcreeper);
                                            p.sendMessage("§2§l» §aRocketCreeper added to §7" + target.getName() + "§a's inventory!");
                                        } else {
                                            p.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                        }
                                    } catch (Exception ex) {
                                        p.sendMessage("§4§l» §cThe third argument must be an Integer!");
                                    }
                                } else {
                                    p.sendMessage("§7§l» §cUnable to find a player by that username!");
                                }
                            } else {
                                p.sendMessage("§4§l» §cIncorrect Syntax! Try: /rocketcreeper give <name> [amount]");
                            }
                            break;

                        default:
                            p.performCommand("rocketcreeper");
                            break;
                    }
                }
            } else {
                p.sendMessage("§4§l» §cYou do not have permission for this command!");
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage("§6§L» §eShowing RocketCreeper commands:");
                sender.sendMessage("§e/rocketcreeper give <name> [amount] - Give a user a Rocket Creeper");
            } else {
                String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                switch (args[0].toLowerCase()) {
                    case "give":
                        if (subArgs.length == 1) {
                            Player target = Bukkit.getPlayer(subArgs[0]);
                            if (target!=null) {
                                ItemStack rocketcreeper = RocketCreepers.getRocketCreeper(1);
                                if (target.getInventory().firstEmpty()!=-1) {
                                    target.getInventory().addItem(rocketcreeper);
                                    sender.sendMessage("§2§l» §aRocketCreeper added to §7" + target.getName() + "§a's inventory!");
                                } else {
                                    sender.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                }
                            } else {
                                sender.sendMessage("§7§l» §cUnable to find a player by that username!");
                            }
                        } else if (subArgs.length == 2) {
                            Player target = Bukkit.getPlayer(subArgs[0]);
                            if (target!=null) {
                                try {
                                    int amount = Integer.parseInt(subArgs[1]);
                                    ItemStack rocketcreeper = RocketCreepers.getRocketCreeper(amount);
                                    if (target.getInventory().firstEmpty()!=-1) {
                                        target.getInventory().addItem(rocketcreeper);
                                        sender.sendMessage("§2§l» §aRocketCreeper added to §7" + target.getName() + "§a's inventory!");
                                    } else {
                                        sender.sendMessage("§4§l» §cThat player doesn't have a free slot in their inventory!");
                                    }
                                } catch (Exception ex) {
                                    sender.sendMessage("§4§l» §cThe third argument must be an Integer!");
                                }
                            } else {
                                sender.sendMessage("§7§l» §cUnable to find a player by that username!");
                            }
                        } else {
                            sender.sendMessage("§4§l» §cIncorrect Syntax! Try: /RocketCreeper give <name> [creator]");
                        }
                        break;

                    default:
                        sender.sendMessage("§6§L» §eShowing RocketCreeper commands:");
                        sender.sendMessage("§e/rocketcreeper give <name> [amount] - Give a user a rocketcreeper");
                        break;
                }
            }
        }
        return false;
    }
}
