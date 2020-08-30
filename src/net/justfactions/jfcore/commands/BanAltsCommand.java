package net.justfactions.jfcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanAltsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.isOp()) {
            if (args.length == 1) {
                String alts = args[0];
                for (String alt : alts.split(":")) {
                    if (alt.replaceAll(" ", "").length() != 0) {
                        Bukkit.getServer().dispatchCommand(sender, "ban -s " + alt + " Compromised Account");
                    }
                }
            } else {
                sender.sendMessage("Syntax: /banalts LIST:OF:NAMES");
            }
        } else {
            sender.sendMessage("§4§l» §cYou do not have access to this command!");
        }
        return true;
    }
}
