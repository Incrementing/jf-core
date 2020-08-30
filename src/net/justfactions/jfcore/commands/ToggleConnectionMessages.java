package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleConnectionMessages implements CommandExecutor {

    Core core;

    public ToggleConnectionMessages(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.isOp()) {
            if (core.getConfig().getBoolean("connection-messages", true)) {
                core.getConfig().set("connection-messages", false);
            } else {
                core.getConfig().set("connection-messages", true);
            }
            sender.sendMessage("§6§l» §eConnection messages have been " + (core.getConfig().getBoolean("connection-messages", true) ? "§aenabled" : "§cdisabled") + "§e!");
        } else {
            sender.sendMessage("§4§l» §cYou do not have permission to do that!");
        }
        return false;
    }
}
