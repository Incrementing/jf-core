package net.justfactions.jfcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage("§6§l» §eYou can join our discord by clicking the link below!");
        sender.sendMessage("§6§l» §a§ohttps://discord.arespvp.com");
        return false;
    }
}
