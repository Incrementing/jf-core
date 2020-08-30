package net.justfactions.jfcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage("§6§l» §eYou can vote for us by clicking the link below!");
        sender.sendMessage("§6§l» §a§ohttps://vote.arespvp.com");
        sender.sendMessage("§6§l» §eYou will get one vote key and $2500!");
        return false;
    }
}
