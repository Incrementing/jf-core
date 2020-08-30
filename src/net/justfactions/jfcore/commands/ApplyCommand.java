package net.justfactions.jfcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ApplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage("§6§l» §eYou can apply for helper by clicking the link below!");
        sender.sendMessage("§6§l» §a§ohttps://arespvp.com/applicationform/helper.2/form");
        return false;
    }
}
